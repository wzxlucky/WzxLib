package com.one.wsy.wzxlib.treeview.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.treeview.bean.LevelItemChild;
import com.one.wsy.wzxlib.treeview.bean.LevelItemParent;

import java.util.List;

/**
 * 描述：
 * 名称: ExpandableItemAdapter
 * 版本: 1.0
 * 日期: 2018/8/29 9:31
 *
 * @author wsy
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    List<MultiItemEntity> data;

    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        this.data = data;
        addItemType(TYPE_LEVEL_0, R.layout.rv_item_parent);
        addItemType(TYPE_LEVEL_1, R.layout.rv_item_child);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                final LevelItemParent lv0 = (LevelItemParent) item;
                helper.setText(R.id.tv, lv0.title);
                helper.setImageResource(R.id.iv, lv0.isExpanded() ? R.mipmap.me_bottom : R.mipmap.me_right_arrow);
                helper.setImageResource(R.id.ivCB, lv0.isCheck ? R.mipmap.cb_check : R.mipmap.cb_uncheck);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                helper.getView(R.id.ivCB).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lv0.isCheck) {
                            helper.setImageResource(R.id.ivCB, R.mipmap.cb_uncheck);
                            setChildNoCheck(lv0);
                            lv0.isCheck = false;
                        } else {
                            helper.setImageResource(R.id.ivCB, R.mipmap.cb_check);
                            setChildCheck(lv0);
                            lv0.isCheck = true;
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final LevelItemChild lv1 = (LevelItemChild) item;
                helper.setText(R.id.tv, lv1.title);
                helper.setImageResource(R.id.iv, lv1.imageUrl);
                helper.setImageResource(R.id.ivCB, lv1.isCheck ? R.mipmap.cb_check : R.mipmap.cb_uncheck);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lv1.isCheck) {
                            helper.setImageResource(R.id.ivCB, R.mipmap.cb_uncheck);
                            lv1.isCheck = false;
                            setParentNoCheck(lv1);
                        } else {
                            helper.setImageResource(R.id.ivCB, R.mipmap.cb_check);
                            lv1.isCheck = true;
                            setParentCheck(lv1);
                        }
                    }
                });
                break;

        }
    }

    private void setParentNoCheck(LevelItemChild lv1) {
        boolean result = false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getItemType() == 0) {
                if (((LevelItemParent) data.get(i)).parentId.equals(lv1.parentId)) {
                    for (int j = 0; j < ((LevelItemParent) data.get(i)).getSubItems().size(); j++) {
                        if (((LevelItemParent) data.get(i)).getSubItems().get(j).isCheck) {
                            result = result || true;
                        } else {
                            result = result || false;
                        }
                    }
                    if (result) {
                        ((LevelItemParent) data.get(i)).isCheck = true;
                    } else {
                        ((LevelItemParent) data.get(i)).isCheck = false;
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    private void setParentCheck(LevelItemChild lv1) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getItemType() == 0) {
                if (((LevelItemParent) data.get(i)).parentId.equals(lv1.parentId)) {
                    ((LevelItemParent) data.get(i)).isCheck = true;
                }
            }
        }
        notifyDataSetChanged();
    }

    private void setChildNoCheck(LevelItemParent lv0) {
        for (int i = 0; i < lv0.getSubItems().size(); i++) {
            lv0.getSubItems().get(i).isCheck = false;
        }
        notifyDataSetChanged();
    }

    private void setChildCheck(LevelItemParent lv0) {
        for (int i = 0; i < lv0.getSubItems().size(); i++) {
            lv0.getSubItems().get(i).isCheck = true;
        }
        notifyDataSetChanged();
    }


}
