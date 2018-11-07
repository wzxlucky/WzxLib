package com.one.wsy.wzxlib.city.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SectionIndexer;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.city.bean.CityListBean;

import java.util.List;

/**
 * 描述：
 * 名称: MultipleBottomAdapter
 *
 * @author: wsy
 * 版本: 1.0
 * 日期: 2018/10/29 14:42
 */
public class MultipleBottomAdapter extends BaseQuickAdapter<CityListBean, BaseViewHolder> implements SectionIndexer {

    private List<CityListBean> data;

    public MultipleBottomAdapter(int layoutResId, @Nullable List<CityListBean> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, CityListBean item) {
        int section = getSectionForPosition(helper.getAdapterPosition() - 1);
        if ((helper.getAdapterPosition() - 1) == getPositionForSection(section)) {
            helper.getView(R.id.tvHead).setVisibility(View.VISIBLE);
            helper.setText(R.id.tvHead, String.valueOf(item.getSortLetters().charAt(0)));
        } else {
            helper.getView(R.id.tvHead).setVisibility(View.GONE);
        }

        helper.setText(R.id.tvContent, item.getCityName());
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < data.size(); i++) {
            String sortStr = data.get(i).getSortLetters();
            char firstChar = sortStr.charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return data.get(position).getSortLetters().charAt(0);
    }


    public int getPostitionByLetter(String letter) {
        int result = -1;
        for (int i = 0; i < data.size(); i++) {
            if (String.valueOf(data.get(i).getSortLetters().toUpperCase().charAt(0)).equals(letter)) {
                result = i+1;
                break;
            }
        }
        return result;
    }

}
