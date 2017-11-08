package com.kivii.grabdoll.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.databinding.ItemMailListBinding;
import com.kivii.grabdoll.ui.entity.Contact;

import java.util.List;

public class MailListAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private LayoutInflater inflater;
    private List<Contact> contactList;

    public MailListAdapter(Context context, List<Contact> contactList) {
        this.inflater = LayoutInflater.from(context);
        this.contactList = contactList;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_mail_list, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ItemMailListBinding binding = holder.getBinding();
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
