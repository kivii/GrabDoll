package com.kivii.grabdoll.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.CustomerStorage;
import com.kivii.grabdoll.core.dao.CustomerStorageDao;
import com.kivii.grabdoll.databinding.ActivityCustomerStorageCreateBinding;
import com.kivii.grabdoll.ui.entity.MsgEvent;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

public class CustomerStorageCreateActivity extends BaseActivity {
    private ActivityCustomerStorageCreateBinding mBinding;

    private CustomerStorage storage;
    private CustomerStorageDao storageDao;
    private boolean isAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_customer_storage_create);
        storageDao = DaoUtils.daoSession.getCustomerStorageDao();
        initView();
    }

    private void initView() {
        mBinding.setPresenter(new Presenter());
        long id = getIntent().getLongExtra("id", 0L);
        storage = storageDao.load(id);
        isAdd = storage == null;
        if (isAdd) {
            storage = new CustomerStorage();
            storage.setNumber(getNumber());

            setTitleName("添加");
        } else {
            setTitleName("编辑");
        }
        mBinding.setStorage(storage);
    }

    public int getNumber() {
        int i = 1;
        while (storageDao.queryBuilder().where(CustomerStorageDao.Properties.Number.eq(i)).count() > 0) {
            i++;
        }
        return i;
    }

    public class Presenter {
        public void onClickSave(View v) {
            if (mBinding.getClickable()) {
                String name = mBinding.etName.getText().toString();
                String mobile = mBinding.etMobile.getText().toString();
                storage.setName(name);
                storage.setMobile(mobile);
                if (isAdd) {
                    storage.setAddTime(new Date());
                    storage.setOrgId(SPUtils.getLong(Constant.KEY_STORE_ID));
                    storageDao.insert(storage);
                } else {
                    storageDao.update(storage);
                }
                EventBus.getDefault().post(new MsgEvent(MsgEvent.REFRESH_DATA));
                onBackPressed();
            }
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mBinding.setClickable(mBinding.etName.length() > 0 && mBinding.etMobile.length() > 0);
        }
    }
}
