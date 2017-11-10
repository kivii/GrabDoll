package com.kivii.grabdoll.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.CustomerStorageRecord;
import com.kivii.grabdoll.core.bean.User;
import com.kivii.grabdoll.ui.view.NumberInputView;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;

import java.util.Date;

public class CustomerStorageRecordCreateActivity extends BaseActivity {
    private NumberInputView numberInputView;
    private EditText etRemark;

    private long storageId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_storage_record_create);
        initView();
    }

    private void initView() {
        storageId = getIntent().getLongExtra("id", 0L);
        user = DaoUtils.daoSession.getUserDao().load(SPUtils.getLong(Constant.KEY_USER_ID));
        if (storageId == 0L || user == null) {
            finish();
            return;
        }

        setTitleName("添加记录");
//        numberInputView = findViewById(R.id.number_input_view);
//        numberInputView.setMaxValue(10000);
//        numberInputView.setMinValue(-10000);
        etRemark = findViewById(R.id.et_remark);
    }

    public void onClickSave(View v) {
        CustomerStorageRecord record = new CustomerStorageRecord();
        record.setAddTime(new Date());
        record.setCount(numberInputView.getValue());
        record.setDescribe(etRemark.getText().toString());
        record.setStorageId(storageId);
        record.setUserName(user.getName());
        record.setUserNum(user.getNumber());
        DaoUtils.daoSession.getCustomerStorageRecordDao().insert(record);
        setResult(RESULT_OK);
        onBackPressed();
    }
}
