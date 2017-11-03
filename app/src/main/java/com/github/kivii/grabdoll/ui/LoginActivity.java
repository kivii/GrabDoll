package com.github.kivii.grabdoll.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.kivii.grabdoll.R;
import com.github.kivii.grabdoll.databinding.ActivityLoginBinding;


public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }

    private void initView() {
        mBinding.setPresenter(new Presenter());
    }

    public class Presenter {

        public void onClickLogin(View v) {

        }

        public void onClickRegister(View v) {
            startActivity(new Intent(LoginActivity.this, CreateOrgActivity.class));
        }
    }
}

