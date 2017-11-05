package com.kivii.grabdoll.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.User;
import com.kivii.grabdoll.databinding.ActivityMainBinding;
import com.kivii.grabdoll.ui.fragment.ChartFragment;
import com.kivii.grabdoll.ui.fragment.HomeFragment;
import com.kivii.grabdoll.ui.fragment.MessageFragment;
import com.kivii.grabdoll.ui.fragment.MineFragment;
import com.kivii.grabdoll.util.AppUtils;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private List<Fragment> fragmentList = new ArrayList<>();
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        long userId = (long) SPUtils.get(this, Constant.KEY_USER_ID, 0L);
        mUser = DaoUtils.daoSession.getUserDao().loadDeep(userId);
        if (mUser == null) {
            finish();
            return;
        }

        if (hasData()) {
            initView();
        } else {
            toCreate();
        }
    }

    @Override
    public void onBackPressed() {
        AppUtils.backHome(this);
    }

    private void toCreate() {

    }

    private boolean hasData() {
        return !DaoUtils.daoSession.getOrganizationDao().loadDeep(mUser.getOrgId()).getGroupList().isEmpty();
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
            mBinding.viewPager.setCurrentItem(3);
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
