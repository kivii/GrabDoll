package com.kivii.grabdoll.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.Toys;
import com.kivii.grabdoll.databinding.ItemToysManagerBinding;

import java.util.List;

public class ToysManagerAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private LayoutInflater inflater;
    private List<Toys> toysList;
    private OnEditClickListener onEditClickListener;

    public ToysManagerAdapter(Context context, List<Toys> groupList) {
        this.inflater = LayoutInflater.from(context);
        this.toysList = groupList;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_toys_manager, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final Toys toys = toysList.get(position);
        ItemToysManagerBinding binding = holder.getBinding();
        binding.setName(toys.getName());
        binding.icEdit.setOnClickListener((v) -> {
            if (onEditClickListener != null) {
                onEditClickListener.onClick(toys);
            }
        });
    }

    @Override
    public int getItemCount() {
        return toysList.size();
    }

    public void add(Toys toys) {
        toysList.add(toys);
        notifyItemInserted(getItemCount());
    }

    public void setOnEditClickListener(OnEditClickListener onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
    }

    public void remove(Toys toys) {
        if (toysList.contains(toys)) {
            int index = toysList.indexOf(toys);
            toysList.remove(index);
            notifyItemRemoved(index);
            notifyItemRangeChanged(0, getItemCount());
        }
    }

    public interface OnEditClickListener {
        void onClick(Toys toys);
    }
}
