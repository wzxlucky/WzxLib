package com.one.wsy.wzxlib.multilayout.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.multilayout.bean.MultipleItem;

import java.util.List;

/**
 * @author wsy
 */
public class RecyclerViewAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public RecyclerViewAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_ONE, R.layout.rv_adapter_item_one);
        addItemType(MultipleItem.ITEM_TWO, R.layout.rv_adapter_item_two);
        addItemType(MultipleItem.ITEM_THREE, R.layout.rv_adapter_item_three);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.ITEM_ONE:
                helper.setText(R.id.tvOne, "这是第一个布局");
                break;
            case MultipleItem.ITEM_TWO:
                helper.setText(R.id.tvTwo, "这是第二个布局");
                break;
            case MultipleItem.ITEM_THREE:
                helper.setText(R.id.tvThree, "这是第三个布局");
                break;
        }
    }
}
