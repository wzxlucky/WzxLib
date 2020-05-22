package com.mrcc.mall.multilayout.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author wsy
 */
public class MultipleItem implements MultiItemEntity {
    public static final int ITEM_ONE = 1;
    public static final int ITEM_TWO = 2;
    public static final int ITEM_THREE = 3;

    private int itemType;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
