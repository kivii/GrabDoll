package com.kivii.grabdoll.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.databinding.FragmentMessageBinding;


public class MessageFragment extends Fragment {
    private Context mContext;
    private FragmentMessageBinding mBinding;

    public MessageFragment() {
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(mContext));

    }
}
