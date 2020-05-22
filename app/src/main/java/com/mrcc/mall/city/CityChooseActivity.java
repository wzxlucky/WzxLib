package com.mrcc.mall.city;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mrcc.mall.R;
import com.mrcc.mall.base.BaseActivity;
import com.mrcc.mall.city.adapter.MultipleBottomAdapter;
import com.mrcc.mall.city.adapter.MultipleCenterAdapter;
import com.mrcc.mall.city.bean.CityListBean;
import com.mrcc.mall.city.bean.HotCityBean;
import com.mrcc.mall.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 描述：仿美团城市选择
 *
 * @author wsy
 */
public class CityChooseActivity extends BaseActivity {

    private RelativeLayout rlBack;
    private TextView tvTitleName;

    private MyLetterView myLetterView;
    private RecyclerView rv, rvCenter;
    private MultipleBottomAdapter multipleBottomAdapter;
    private MultipleCenterAdapter multipleCenterAdapter;

    private TextView tvLocation, tvText;
    private View topView, centerView;

    private EditText etCity;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_city_choose);
    }

    @Override
    public void initView() {
        rlBack = findView(R.id.rlBack);
        tvTitleName = findView(R.id.tvTitleName);
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance(CityChooseActivity.this)));
        getHotList();
        getAllList();
        etCity = findViewById(R.id.etCity);
        myLetterView = findViewById(R.id.myLetterView);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        multipleBottomAdapter = new MultipleBottomAdapter(R.layout.multiple_bottom_adp_item, cityListBeanList);
        rv.setAdapter(multipleBottomAdapter);
        topView = getLayoutInflater().inflate(R.layout.multiple_top, (ViewGroup) rv.getParent(), false);
        tvText = topView.findViewById(R.id.tvText);
        tvLocation = topView.findViewById(R.id.tvLocation);
        tvText.setText("长春");
        centerView = getLayoutInflater().inflate(R.layout.multiple_center, (ViewGroup) rv.getParent(), false);
        rvCenter = centerView.findViewById(R.id.rvCenter);
        rvCenter.setLayoutManager(new GridLayoutManager(this, 3));
        multipleCenterAdapter = new MultipleCenterAdapter(R.layout.multiple_center_adp_item, hotCityBeanList);
        multipleCenterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SharedPreferencesUtil.put(CityChooseActivity.this, "ChooseCity", hotCityBeanList.get(position).getHotCitName());
                finish();
            }
        });
        rvCenter.setAdapter(multipleCenterAdapter);
        multipleBottomAdapter.addHeaderView(topView);
        multipleBottomAdapter.addHeaderView(centerView);
        multipleBottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SharedPreferencesUtil.put(CityChooseActivity.this, "ChooseCity", ((List<CityListBean>) adapter.getData()).get(position).getCityName());
                finish();
            }
        });


    }

    @Override
    public void initData() {
        tvTitleName.setText("城市选择");
        rlBack.setOnClickListener(this);
        tvText.setOnClickListener(this);
        myLetterView.setOnSlidingListener(new MyLetterView.OnSlidingListener() {
            @Override
            public void sliding(String str) {
                Toast.makeText(CityChooseActivity.this, str, Toast.LENGTH_SHORT).show();
                if (ValidationUtil.isNotEmpty(etCity.getText().toString().trim())) {
                    rv.scrollToPosition(0);
                } else {
                    switch (str) {
                        case "当前":
                            rv.scrollToPosition(0);
                            break;
                        case "热门":
                            ((LinearLayoutManager) rv.getLayoutManager()).scrollToPositionWithOffset(0, -topView.getHeight());
                            break;
                        default:
                            ((LinearLayoutManager) rv.getLayoutManager()).scrollToPositionWithOffset(multipleBottomAdapter.getPostitionByLetter(str), 0);
                    }
                }
            }
        });

        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                rv.scrollToPosition(0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.rlBack:
                finish();
                break;
            case R.id.tvText:
                SharedPreferencesUtil.put(CityChooseActivity.this, "ChooseCity", tvText.getText().toString().trim());
                finish();
                break;
            default:
        }
    }

    /**
     * 搜索城市或拼音
     *
     * @param str
     */
    private void filterData(String str) {
        List<CityListBean> searchCityListBeanList = new ArrayList<>();
        if (ValidationUtil.isEmpty(str)) {
            getAllList();
            multipleBottomAdapter.setNewData(cityListBeanList);
        } else {
            for (CityListBean sortModel : cityListBeanList) {
                String name = sortModel.getCityName();
                if (name.toUpperCase().indexOf(str.toString().toUpperCase()) != -1 || Pinyin.toPinyin(sortModel.getSortLetters(), "").toUpperCase().startsWith(str.toString().toUpperCase())) {
                    searchCityListBeanList.add(sortModel);
                }
            }
            multipleBottomAdapter.setNewData(searchCityListBeanList);
        }
    }


    /**
     * 获取城市列表
     */
    private List<CityListBean> cityListBeanList;

    private void getAllList() {
        String[] strAllCity = getResources().getStringArray(R.array.cityName);
        cityListBeanList = new ArrayList<>();
        for (int i = 0; i < strAllCity.length; i++) {
            CityListBean cityListBean = new CityListBean();
            cityListBean.setCityName(strAllCity[i]);
            cityListBean.setSortLetters(Pinyin.toPinyin(cityListBean.getCityName(), "").toUpperCase());
            cityListBeanList.add(cityListBean);
        }
        Collections.sort(cityListBeanList, new PinyinComparator());
    }


    /**
     * 获取热门城市
     */

    private List<HotCityBean> hotCityBeanList;
    private String[] strHotCity = new String[]{"北京", "上海", "广州", "深圳", "成都", "天津", "南京", "武汉", "西安"};

    private void getHotList() {
        hotCityBeanList = new ArrayList<>();
        for (int i = 0; i < strHotCity.length; i++) {
            HotCityBean hotCityBean = new HotCityBean();
            hotCityBean.setHotCitName(strHotCity[i]);
            hotCityBeanList.add(hotCityBean);
        }
    }


}
