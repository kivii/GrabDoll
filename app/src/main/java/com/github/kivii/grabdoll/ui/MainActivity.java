package com.github.kivii.grabdoll.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.kivii.grabdoll.R;
import com.github.kivii.grabdoll.databinding.ActivityMainBinding;
import com.github.kivii.grabdoll.ui.fragment.ChartFragment;
import com.github.kivii.grabdoll.ui.fragment.HomeFragment;
import com.github.kivii.grabdoll.ui.fragment.MessageFragment;
import com.github.kivii.grabdoll.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        HomeFragment homeFragment = new HomeFragment();
        MessageFragment msgFragment = new MessageFragment();
        ChartFragment chartFragment = new ChartFragment();
        MineFragment mineFragment = new MineFragment();

        fragmentList.clear();
        fragmentList.add(homeFragment);
        fragmentList.add(msgFragment);
        fragmentList.add(chartFragment);
        fragmentList.add(mineFragment);

        mBinding.viewPager.setOffscreenPageLimit(3);
        mBinding.viewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), fragmentList));
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBinding.setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.setPresenter(new Presenter());
    }

    public class Presenter {
        public void onClickAdd(View v) {

        }

        public void onClickHome(View v) {
            mBinding.viewPager.setCurrentItem(0);
        }

        public void onClickMessage(View v) {
            mBinding.viewPager.setCurrentItem(1);
        }

        public void onClickChart(View v) {
            mBinding.viewPager.setCurrentItem(2);
        }

        public void onClickMine(View v) {
//            mBinding.viewPager.setCurrentItem(3);
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public VpAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
