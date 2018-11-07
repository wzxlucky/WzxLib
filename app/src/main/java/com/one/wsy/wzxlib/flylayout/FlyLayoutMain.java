package com.one.wsy.wzxlib.flylayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SlidingTabLayout;
import com.one.wsy.wzxlib.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Fragment+viewPager 禁止预加载
 *
 * @author wsy
 */
public class FlyLayoutMain extends AppCompatActivity {

    private static ArrayList<FlyLayoutFragment> fragmentArrayList;

    private static final String[] titles = {"星期一", "星期二", "星期三", "星期四", "星期五"};

    private MyPagerAdapter mAdapter;

    private boolean isFirst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fly_layout_main);
        SlidingTabLayout slidingTabLayout = findViewById(R.id.slidingTabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        fragmentArrayList = new ArrayList<>();
        for (String title : titles) {
            fragmentArrayList.add(FlyLayoutFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        slidingTabLayout.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (!isFirst) {
                    EventBus.getDefault().post(new MessageEvent(String.valueOf(0)));
                    isFirst = true;
                }
            }

            @Override
            public void onPageSelected(int position) {
                EventBus.getDefault().post(new MessageEvent(String.valueOf(position)));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

    }

}
