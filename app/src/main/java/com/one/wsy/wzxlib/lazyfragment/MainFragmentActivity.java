package com.one.wsy.wzxlib.lazyfragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：Fragment+ViewPager禁止预加载
 * 名称: MainFragmentActivity
 *
 * @author: wsy
 * 版本: 1.0
 * 日期: 2018/11/6 16:33
 */
public class MainFragmentActivity extends BaseActivity {

    private ViewPager vp;
    private TextView tvOne, tvTwo, tvThree, tvFour;

    private boolean isFirst;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_fragment_main);
    }

    @Override
    public void initView() {
        tvOne = findViewById(R.id.tvOne);
        tvTwo = findViewById(R.id.tvTwo);
        tvThree = findViewById(R.id.tvThree);
        tvFour = findViewById(R.id.tvFour);
        vp = findViewById(R.id.vp);

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        vp.setOffscreenPageLimit(0);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }


        });


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (!isFirst) {
                    tvOne.setTextColor(Color.parseColor("#FF5138"));
                    tvTwo.setTextColor(Color.parseColor("#000000"));
                    tvThree.setTextColor(Color.parseColor("#000000"));
                    tvFour.setTextColor(Color.parseColor("#000000"));
                    isFirst = true;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tvOne.setTextColor(Color.parseColor("#FF5138"));
                        tvTwo.setTextColor(Color.parseColor("#000000"));
                        tvThree.setTextColor(Color.parseColor("#000000"));
                        tvFour.setTextColor(Color.parseColor("#000000"));
                        break;
                    case 1:
                        tvOne.setTextColor(Color.parseColor("#000000"));
                        tvTwo.setTextColor(Color.parseColor("#FF5138"));
                        tvThree.setTextColor(Color.parseColor("#000000"));
                        tvFour.setTextColor(Color.parseColor("#000000"));
                        break;
                    case 2:
                        tvOne.setTextColor(Color.parseColor("#000000"));
                        tvTwo.setTextColor(Color.parseColor("#000000"));
                        tvThree.setTextColor(Color.parseColor("#FF5138"));
                        tvFour.setTextColor(Color.parseColor("#000000"));
                        break;
                    case 3:
                        tvOne.setTextColor(Color.parseColor("#000000"));
                        tvTwo.setTextColor(Color.parseColor("#000000"));
                        tvThree.setTextColor(Color.parseColor("#000000"));
                        tvFour.setTextColor(Color.parseColor("#FF5138"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void initData() {
        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOne.setTextColor(Color.parseColor("#FF5138"));
                tvTwo.setTextColor(Color.parseColor("#000000"));
                tvThree.setTextColor(Color.parseColor("#000000"));
                tvFour.setTextColor(Color.parseColor("#000000"));
                vp.setCurrentItem(0);
            }
        });

        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOne.setTextColor(Color.parseColor("#000000"));
                tvTwo.setTextColor(Color.parseColor("#FF5138"));
                tvThree.setTextColor(Color.parseColor("#000000"));
                tvFour.setTextColor(Color.parseColor("#000000"));
                vp.setCurrentItem(1);
            }
        });

        tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOne.setTextColor(Color.parseColor("#000000"));
                tvTwo.setTextColor(Color.parseColor("#000000"));
                tvThree.setTextColor(Color.parseColor("#FF5138"));
                tvFour.setTextColor(Color.parseColor("#000000"));
                vp.setCurrentItem(2);
            }
        });

        tvFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOne.setTextColor(Color.parseColor("#000000"));
                tvTwo.setTextColor(Color.parseColor("#000000"));
                tvThree.setTextColor(Color.parseColor("#000000"));
                tvFour.setTextColor(Color.parseColor("#FF5138"));
                vp.setCurrentItem(3);
            }
        });
    }

    @Override
    public void onClickEvent(View v) {

    }
}
