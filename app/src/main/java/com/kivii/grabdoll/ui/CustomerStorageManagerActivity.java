package com.kivii.grabdoll.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.CustomerStorage;
import com.kivii.grabdoll.core.bean.Organization;
import com.kivii.grabdoll.core.dao.CustomerStorageDao;
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
    private List<CustomerStorage> allList = new ArrayList<>();

    private Organization org;
    private CustomerStorageDao storageDao;
    private static final int REQUEST_CREATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_customer_storage_manager);

        storageDao = DaoUtils.daoSession.getCustomerStorageDao();
        long orgId = SPUtils.getLong(Constant.KEY_STORE_ID);
        org = DaoUtils.daoSession.getOrganizationDao().loadDeep(orgId);
        if (org == null) {
            finish();
            return;
        }

        initView();
        initData();
    }

    private void initView() {
        mBinding.setPresenter(new Presenter());
        setTitleName("顾客储存");
        setRightIc(R.string.ic_plus, v -> addCustomer(0));

        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvContent.setAdapter(mAdapter = new CustomerStorageManagerAdapter(this, storageList));
        mBinding.rvContent.addItemDecoration(new SimpleItemDecoration());

        mAdapter.setOnItemClickListener(storage -> toRecord(storage.getId()));
        mAdapter.setOnItemLongClickListener(this::showSelect);
    }

    private void initData() {
        List<CustomerStorage> list = org.getStorageList();
        if (list != null && !list.isEmpty()) {
            allList.clear();
            allList.addAll(list);
            storageList.clear();
            storageList.addAll(list);
            mAdapter.notifyDataSetChanged();
        } else {
            addCustomer(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CREATE:
                org.resetStorageList();
                initData();
                break;
        }
    }

    private void addCustomer(long id) {
        Intent intent = new Intent(this, CustomerStorageCreateActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, REQUEST_CREATE);
    }

    private void toRecord(long id) {
        Intent intent = new Intent(this, CustomerStorageRecordActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void showSelect(CustomerStorage storage) {
        boolean isTop = storage.getTop() > 0;

        new AlertDialog.Builder(this)
                .setItems(new String[]{"编辑", "删除", isTop ? "取消置顶" : "置顶"},
                        (dialog, which) -> {
                    switch (which) {
                        case 0:
                            addCustomer(storage.getId());
                            break;
                        case 1:
                            mAdapter.delete(storage);
                            storage.delete();
                            org.resetStorageList();
                            break;
                        case 2:
                            storage.setTop(isTop ? 0 : 1);
                            storage.update();
                            org.resetStorageList();
                            initData();
                            break;

                    }
                })
                .show();
    }

    private void search(String str) {
        storageList.clear();
        if (TextUtils.isEmpty(str)) {
            storageList.addAll(allList);
            mAdapter.notifyDataSetChanged();
        } else {
            for (CustomerStorage storage : allList) {
                if (String.valueOf(storage.getNumber()).contains(str) ||
                        storage.getName().contains(str) ||
                        storage.getMobile().contains(str)) {
                    storageList.add(storage);
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    public class Presenter {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            search(s.toString().trim());
        }
    }
}
