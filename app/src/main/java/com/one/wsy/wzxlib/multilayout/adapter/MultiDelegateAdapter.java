package com.one.wsy.wzxlib.multilayout.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.multilayout.bean.Entity;

import java.util.List;

public class MultiDelegateAdapter extends BaseQuickAdapter<Entity, BaseViewHolder> {

    public MultiDelegateAdapter(List<Entity> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<Entity>() {
            @Override
            protected int getItemType(Entity entity) {

                return entity.getAdaterType();
            }
        });

        getMultiTypeDelegate().registerItemType(Entity.ITEM_ONE, R.layout.rv_adapter_item_one)
                .registerItemType(Entity.ITEM_TWO, R.layout.rv_adapter_item_two)
                .registerItemType(Entity.ITEM_THREE, R.layout.rv_adapter_item_three);
    }

    @Override
    protected void convert(BaseViewHolder helper, Entity item) {
        switch (item.getAdaterType()) {
            case Entity.ITEM_ONE:
                helper.setText(R.id.tvOne, "这是第一个布局");
                break;
            case Entity.ITEM_TWO:
                helper.setText(R.id.tvTwo, "这是第二个布局");
                break;
            case Entity.ITEM_THREE:
                helper.setText(R.id.tvThree, "这是第三个布局");
                break;
        }

    }
}
