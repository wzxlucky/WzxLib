package com.mrcc.mall.leftrightlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mrcc.mall.R;
import com.mrcc.mall.leftrightlayout.adapter.RecyclerViewLeftAdapter;
import com.mrcc.mall.leftrightlayout.adapter.RecyclerViewRightAdapter;
import com.mrcc.mall.leftrightlayout.bean.LeftBean;
import com.mrcc.mall.leftrightlayout.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：RecyclerView 左右联动
 *
 * @author wsy
 */
public class LeftRightRecyclerMain extends AppCompatActivity {

    RecyclerView rvLeft, rvRight;
    RecyclerViewLeftAdapter rvLeftAdapter;
    RecyclerViewRightAdapter rvRightAdapter;

    private List<RightBean> rightBeanList;
    private List<LeftBean> leftBeanList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_right_main);
        rvLeft = findViewById(R.id.rvLeft);
        rvRight = findViewById(R.id.rvRight);

        rvLeft.setLayoutManager(new LinearLayoutManager(this));
        rvRight.setLayoutManager(new LinearLayoutManager(this));
        rvLeft.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        leftBeanList = new ArrayList<>();

        leftBeanList.add(new LeftBean("內科"));
        leftBeanList.add(new LeftBean("儿科"));
        leftBeanList.add(new LeftBean("妇产科"));
        leftBeanList.add(new LeftBean("外科"));
        leftBeanList.add(new LeftBean("皮肤性病科"));
        leftBeanList.add(new LeftBean("中医科"));
        leftBeanList.add(new LeftBean("口腔科"));
        leftBeanList.add(new LeftBean("耳鼻喉头颈科"));
        leftBeanList.add(new LeftBean("眼科"));
        leftBeanList.add(new LeftBean("骨科"));
        leftBeanList.add(new LeftBean("肿瘤科"));
        leftBeanList.add(new LeftBean("急诊科"));
        leftBeanList.add(new LeftBean("其他科室"));

        setData(leftBeanList, 0);

        rvLeftAdapter = new RecyclerViewLeftAdapter(R.layout.rv_left, leftBeanList);

        rvRightAdapter = new RecyclerViewRightAdapter(R.layout.rv_right, rightBeanList);

        rvLeftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                rvLeftAdapter.setClickPosition(position);
                rvRightAdapter.setNewData(setData(leftBeanList, position));
                rvRight.smoothScrollToPosition(0);
                rvLeftAdapter.notifyDataSetChanged();
                Toast.makeText(LeftRightRecyclerMain.this, leftBeanList.get(position).getContentLeft(), Toast.LENGTH_SHORT).show();
            }
        });

        rvRightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(LeftRightRecyclerMain.this, rightBeanList.get(position).getContentRight(), Toast.LENGTH_SHORT).show();
            }
        });
        rvLeft.setAdapter(rvLeftAdapter);

        rvRight.setAdapter(rvRightAdapter);
    }

    private List<RightBean> setData(List<LeftBean> leftBeanList, int count) {
        rightBeanList = new ArrayList<>();
        for (int i = 0; i < leftBeanList.size(); i++) {
            rightBeanList.add(new RightBean(leftBeanList.get(i).getContentLeft() + count));
        }

        return rightBeanList;
    }
}
