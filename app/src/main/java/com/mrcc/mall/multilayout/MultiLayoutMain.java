package com.mrcc.mall.multilayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mrcc.mall.R;
import com.mrcc.mall.multilayout.adapter.MultiDelegateAdapter;
import com.mrcc.mall.multilayout.adapter.RecyclerViewAdapter;
import com.mrcc.mall.multilayout.bean.MultipleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：RecyclerView 多布局
 *
 * @author wsy
 */
public class MultiLayoutMain extends AppCompatActivity {

    RecyclerView rv;

    RecyclerViewAdapter recyclerViewAdapter;
    MultiDelegateAdapter multiDelegateAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_main);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));


        List<MultipleItem> multipleItemList = new ArrayList<>();
        multipleItemList.add(new MultipleItem(1));
        multipleItemList.add(new MultipleItem(2));
        multipleItemList.add(new MultipleItem(3));
        recyclerViewAdapter = new RecyclerViewAdapter(multipleItemList);

        List<Entity> entityList = new ArrayList<>();
        entityList.add(new Entity(1));
        entityList.add(new Entity(2));
        entityList.add(new Entity(3));
        multiDelegateAdapter = new MultiDelegateAdapter(entityList);

        rv.setAdapter(multiDelegateAdapter);
    }
}
