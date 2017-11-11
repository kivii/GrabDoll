package com.kivii.grabdoll.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.CustomerStorage;
import com.kivii.grabdoll.core.bean.CustomerStorageRecord;
import com.kivii.grabdoll.core.bean.User;
import com.kivii.grabdoll.ui.entity.MsgEvent;
import com.kivii.grabdoll.ui.view.NumberInputView;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

public class CustomerStorageRecordCreateActivity extends BaseActivity {
    private NumberInputView numberInputView;
    private EditText etRemark;

    private CustomerStorage storage;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_storage_record_create);
        initView();
    }

    private void initView() {
        long storageId = getIntent().getLongExtra("id", 0L);
        storage = DaoUtils.daoSession.getCustomerStorageDao().load(storageId);
        user = DaoUtils.daoSession.getUserDao().load(SPUtils.getLong(Constant.KEY_USER_ID));
        if (storage == null || user == null) {
            finish();
            return;
        }

        setTitleName("添加记录");
        numberInputView = findViewById(R.id.number_input_view);
        etRemark = findViewById(R.id.et_remark);
    }

    public void onClickSave(View v) {
        CustomerStorageRecord record = new CustomerStorageRecord();
        record.setAddTime(new Date());
        record.setCount(numberInputView.getValue());
        record.setDescribe(etRemark.getText().toString());
        record.setStorageId(storage.getId());
        record.setUserName(user.getName());
        record.setUserNum(user.getNumber());
        DaoUtils.daoSession.getCustomerStorageRecordDao().insert(record);

        storage.setCount(storage.getCount() + numberInputView.getValue());
        storage.update();
        EventBus.getDefault().post(new MsgEvent(MsgEvent.REFRESH_DATA));
        onBackPressed();
    }
}
