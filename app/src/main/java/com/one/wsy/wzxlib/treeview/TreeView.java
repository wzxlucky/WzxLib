package com.one.wsy.wzxlib.treeview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.treeview.adapter.ExpandableItemAdapter;
import com.one.wsy.wzxlib.treeview.bean.LevelItemChild;
import com.one.wsy.wzxlib.treeview.bean.LevelItemParent;

import java.util.ArrayList;

/**
 * 描述：RecyclerView 树形结构
 * 名称: TreeView
 * 版本: 1.0
 * 日期: 2018/8/29 15:49
 *
 * @author wsy
 */
public class TreeView extends AppCompatActivity {

    private RecyclerView rv;

    private ExpandableItemAdapter expandableItemAdapter;

    private ArrayList<MultiItemEntity> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_view);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(TreeView.this));
        list = generateData();
        expandableItemAdapter = new ExpandableItemAdapter(list);
        rv.setAdapter(expandableItemAdapter);


        TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < expandableItemAdapter.getData().size(); i++) {
                    if (expandableItemAdapter.getData().get(i).getItemType() == 1) {
                    } else {
                        for (int j = 0; j < ((LevelItemParent) expandableItemAdapter.getData().get(i)).getSubItems().size(); j++) {
                            if (((LevelItemParent) expandableItemAdapter.getData().get(i)).getSubItems().get(j).isCheck) {
                                Log.i("测试", "------: " + ((LevelItemParent) expandableItemAdapter.getData().get(i)).getSubItems().get(j).title);
                            }
                        }
                    }
                }
            }
        });
    }


    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 3;
        int lv1Count = 5;
        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            LevelItemParent lv0 = new LevelItemParent(String.valueOf(i), "第 " + i + "个", false);
            for (int j = 0; j < lv1Count; j++) {
                LevelItemChild lv1 = new LevelItemChild(String.valueOf(i), nameList[j] + "--" + i + "--" + j, R.mipmap.ic_launcher_round, false);
                lv0.addSubItem(lv1);

            }
            res.add(lv0);
        }
        return res;
    }
}
