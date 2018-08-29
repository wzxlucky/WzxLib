package com.one.wsy.wzxlib.treeview.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.one.wsy.wzxlib.treeview.adapter.ExpandableItemAdapter;

/**
 * 描述：
 * 名称: LevelItemChild
 * 作者: wsy
 * 版本: 1.0
 * 日期: 2018/8/29 9:29
 */
public class LevelItemChild implements MultiItemEntity {

    public String parentId;

    public String title;

    public int imageUrl;

    public boolean isCheck;

    public LevelItemChild(String parentId, String title, int imageUrl, boolean isCheck) {
        this.parentId = parentId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.isCheck = isCheck;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}
