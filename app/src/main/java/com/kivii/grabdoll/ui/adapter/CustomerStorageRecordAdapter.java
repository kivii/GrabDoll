package com.kivii.grabdoll.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.CustomerStorageRecord;
import com.kivii.grabdoll.databinding.ItemCustomerStorageRecordBinding;
import com.kivii.grabdoll.util.StringUtils;

import java.util.List;

public class CustomerStorageRecordAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private LayoutInflater inflater;
    private List<CustomerStorageRecord> recordList;

    public CustomerStorageRecordAdapter(Context context, List<CustomerStorageRecord> recordList) {
        this.inflater = LayoutInflater.from(context);
        this.recordList = recordList;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_customer_storage_record, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        CustomerStorageRecord record = recordList.get(position);
        ItemCustomerStorageRecordBinding binding = holder.getBinding();
        binding.tvCount.setText(String.valueOf(record.getCount() > 0 ? "+" +
                record.getCount(): record.getCount()));

        String time = String.format("时间： %s", StringUtils.getTimeStr(record.getAddTime()));
        String name = String.format("操作： %s", record.getUserName());
        String remark = TextUtils.isEmpty(record.getDescribe()) ? null :
                String.format("备注： %s", record.getDescribe());
        String info;
        if (TextUtils.isEmpty(remark)) {
            info = String.format("%s\n%s", time, name);
        } else {
            info = String.format("%s\n%s\n%s", time, name, remark);
        }
        binding.tvInfo.setText(info);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }
}
