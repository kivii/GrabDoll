package com.kivii.grabdoll.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.MachineGroup;
import com.kivii.grabdoll.databinding.ItemGroupManagerBinding;

import java.util.List;

public class GroupManagerAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private LayoutInflater inflater;
    private List<MachineGroup> groupList;
    private OnEditClickListener onEditClickListener;
    private onItemClickListener onItemClickListener;

    public GroupManagerAdapter(Context context, List<MachineGroup> groupList) {
        this.inflater = LayoutInflater.from(context);
        this.groupList = groupList;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_group_manager, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final MachineGroup group = groupList.get(position);
        ItemGroupManagerBinding binding = holder.getBinding();
        binding.setName(group.getName());
        binding.icEdit.setOnClickListener((v) -> {
            if (onEditClickListener != null) {
                onEditClickListener.onClick(group);
            }
        });

        binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(group);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public void add(MachineGroup group) {
        groupList.add(group);
        notifyItemInserted(getItemCount());
    }

    public void setOnEditClickListener(OnEditClickListener onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
    }

    public void setOnItemClickListener(GroupManagerAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void remove(MachineGroup group) {
        if (groupList.contains(group)) {
            int index = groupList.indexOf(group);
            groupList.remove(index);
            notifyItemRemoved(index);
            notifyItemRangeChanged(0, getItemCount());
        }
    }

    public interface OnEditClickListener {
        void onClick(MachineGroup group);
    }

    public interface onItemClickListener {
        void onClick(MachineGroup group);
    }
}
