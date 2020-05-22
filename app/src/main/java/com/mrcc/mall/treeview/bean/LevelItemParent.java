package com.mrcc.mall.treeview.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mrcc.mall.treeview.adapter.ExpandableItemAdapter;

/**
 * @author wsy
 */
public class LevelItemParent extends AbstractExpandableItem<LevelItemChild> implements MultiItemEntity {


    public String parentId;

    public String title;

    public boolean isCheck;

    public LevelItemParent(String parentId, String title, boolean isCheck) {
        this.parentId = parentId;
        this.title = title;
        this.isCheck = isCheck;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }
}
