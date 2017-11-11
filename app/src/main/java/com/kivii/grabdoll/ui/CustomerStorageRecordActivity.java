package com.kivii.grabdoll.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.CustomerStorage;
import com.kivii.grabdoll.core.bean.CustomerStorageRecord;
import com.kivii.grabdoll.ui.adapter.CustomerStorageRecordAdapter;
import com.kivii.grabdoll.ui.adapter.SimpleItemDecoration;
import com.kivii.grabdoll.ui.entity.MsgEvent;
import com.kivii.grabdoll.util.DaoUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerStorageRecordActivity extends BaseActivity {
    private CustomerStorageRecordAdapter mAdapter;
    private List<CustomerStorageRecord> recordList = new ArrayList<>();
    private TextView tvCount;
    private TextView tvInfo;

    private CustomerStorage storage;
    private static final int REQUEST_ADD_RECORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_customer_storage_record);
        initView();
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgEvent(MsgEvent event) {
        if (MsgEvent.REFRESH_DATA.equals(event.message)) {
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initView() {
        long id = getIntent().getLongExtra("id", 0L);
        storage = DaoUtils.daoSession.getCustomerStorageDao().load(id);
        if (storage == null) {
            finish();
            return;
        }

        setTitleName(storage.getName());
        setRightIc(R.string.ic_plus, v -> addRecord());

        tvCount = findViewById(R.id.tv_count);
        RecyclerView rvContent = findViewById(R.id.rv_content);
        tvInfo = findViewById(R.id.tv_info);

        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setAdapter(mAdapter = new CustomerStorageRecordAdapter(this, recordList));
        rvContent.addItemDecoration(new SimpleItemDecoration());
    }

    private void initData() {
        storage.resetRecordList();
        List<CustomerStorageRecord> list = storage.getRecordList();
        if (!list.isEmpty()) {
            tvInfo.setText(String.format(Locale.CHINA, "编号： %d\n手机： %s",
                    storage.getNumber(), storage.getMobile()));
            tvCount.setText(String.valueOf(storage.getCount()));

            recordList.clear();
            recordList.addAll(list);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void addRecord() {
        Intent intent = new Intent(this, CustomerStorageRecordCreateActivity.class);
        intent.putExtra("id", storage.getId());
        startActivityForResult(intent, REQUEST_ADD_RECORD);
    }
}
