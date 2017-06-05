package com.example.orangemodule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.orangemodule.adapter.CommonPagerAdapter;
import com.example.orangemodule.bean.CommonTabBean;
import com.example.orangemodule.fragment.ModuleFragment;
import com.example.orangemodule.fragment.ShopFragment;
import com.example.orangemodule.fragment.UserFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.commonTabLayout)
    CommonTabLayout commonTabLayout;

    private static final int[] SELECTED_IMAGES = {R.drawable.module_selected, R.drawable.shop_selected, R.drawable.user_selected};
    private static final int[] UNSELECTED_IMAGES = {R.drawable.module_unselected, R.drawable.shop_unselected, R.drawable.user_unselected};
    private static final String[] TITLES = {"模块", "商城", "我的"};

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTabLayout();
        initViewPager();
    }

    private void initTabLayout() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            tabEntities.add(new CommonTabBean(SELECTED_IMAGES[i], UNSELECTED_IMAGES[i], TITLES[i]));
        }
        commonTabLayout.setTabData(tabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(1);
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(ModuleFragment.newInstance());
        fragments.add(ShopFragment.newInstance());
        fragments.add(UserFragment.newInstance());
        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }
}
