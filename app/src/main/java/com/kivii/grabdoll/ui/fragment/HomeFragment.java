package com.kivii.grabdoll.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private Context mContext;
    private FragmentHomeBinding mBinding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
//        mBinding.btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Animatable anim = (Animatable) mBinding.image.getDrawable();
//                anim.start();
//            }
//        });
    }
}
