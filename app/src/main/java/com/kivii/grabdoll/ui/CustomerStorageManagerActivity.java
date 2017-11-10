package com.kivii.grabdoll.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.CustomerStorage;
import com.kivii.grabdoll.core.bean.Organization;
import com.kivii.grabdoll.databinding.ActivityCustomerStorageManagerBinding;
import com.kivii.grabdoll.ui.adapter.CustomerStorageManagerAdapter;
import com.kivii.grabdoll.ui.adapter.SimpleItemDecoration;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerStorageManagerActivity extends BaseActivity {
    private ActivityCustomerStorageManagerBinding mBinding;
    private CustomerStorageManagerAdapter mAdapter;
    private List<CustomerStorage> storageList = new ArrayList<>();

    private Organization org;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_customer_storage_manager);

        long orgId = SPUtils.getLong(Constant.KEY_STORE_ID);
        org = DaoUtils.daoSession.getOrganizationDao().loadDeep(orgId);

        initView();
        initData();
    }

    private void initView() {
        mBinding.setPresenter(new Presenter());
        setTitleName("顾客储存");
        setRightIc(R.string.ic_plus, v -> addCustomer());

        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvContent.setAdapter(mAdapter = new CustomerStorageManagerAdapter(this, storageList));
        mBinding.rvContent.addItemDecoration(new SimpleItemDecoration());

        mAdapter.setOnItemClickListener(storage -> {

        });

        mAdapter.setOnItemLongClickListener(storage -> {

        });
    }

    private void initData() {

    }

    private void addCustomer() {

    }

    public class Presenter {
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    }
}
