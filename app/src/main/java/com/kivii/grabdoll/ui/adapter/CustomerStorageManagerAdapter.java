package com.kivii.grabdoll.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.CustomerStorage;
import com.kivii.grabdoll.databinding.ItemCustomerStorageManagerBinding;

import java.util.List;

public class CustomerStorageManagerAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private LayoutInflater inflater;
    private List<CustomerStorage> list;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public CustomerStorageManagerAdapter(Context context, List<CustomerStorage> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_customer_storage_manager, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        CustomerStorage storage = list.get(position);
        ItemCustomerStorageManagerBinding binding = holder.getBinding();
        binding.setStorage(storage);
        binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(storage);
            }
        });

        binding.getRoot().setOnLongClickListener(v -> {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(storage);
                return true;
            }
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(CustomerStorage storage);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(CustomerStorage storage);
    }
}
