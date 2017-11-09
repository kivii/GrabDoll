package com.kivii.grabdoll.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.databinding.ActivityCustomerStorageManagerBinding;

public class CustomerStorageManagerActivity extends BaseActivity {
    private ActivityCustomerStorageManagerBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_customer_storage_manager);
        initView();
    }

    private void initView() {
        mBinding.setPresenter(new Presenter());
        setTitleName("顾客储存");
        setRightIc(R.string.ic_plus, v -> addCustomer());
    }

    private void addCustomer() {

    }

    public class Presenter {
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    }
}
