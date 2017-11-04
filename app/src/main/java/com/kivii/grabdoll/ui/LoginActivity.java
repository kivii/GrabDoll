package com.kivii.grabdoll.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.Organization;
import com.kivii.grabdoll.core.bean.User;
import com.kivii.grabdoll.core.dao.OrganizationDao;
import com.kivii.grabdoll.core.dao.UserDao;
import com.kivii.grabdoll.databinding.ActivityLoginBinding;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;
import com.kivii.grabdoll.util.StringUtils;

public class LoginActivity extends BaseActivity {
    private Context mContext;
    private ActivityLoginBinding mBinding;
    private static final int REQUEST_REGISTER = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mContext = this;
        initView();
    }

    private void initView() {
        mBinding.setPresenter(new Presenter());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_REGISTER:
                showRegisterOkDialog(data);
                break;
        }
    }

    private void showRegisterOkDialog(Intent data) {

        long storeId = data.getLongExtra(Constant.KEY_STORE_ID, 0L);
        long userId = data.getLongExtra(Constant.KEY_USER_ID, 0L);
        String psw = data.getStringExtra(Constant.KEY_USER_PASSWORD);
        if (storeId == 0L || userId == 0L || TextUtils.isEmpty(psw)) {
            toast("注册失败");
            return;
        }

        mBinding.etStoreNumber.setText(String.valueOf(storeId + 999));
        mBinding.etMemberNumber.setText(String.valueOf(userId));
        mBinding.etPassword.setText(psw);

        new AlertDialog.Builder(this)
                .setTitle("注册成功")
                .setMessage("您的店铺帐号为 " + (storeId + 999) +
                        "\n您的工号为 " + userId + "\n请记住了")
                .setPositiveButton("确定", null);
    }

    private void login() {
        String storeNum = mBinding.etStoreNumber.getText().toString();
        String userNum = mBinding.etMemberNumber.getText().toString();
        String psw = mBinding.etPassword.getText().toString();

        if (TextUtils.isEmpty(storeNum)) {
            mBinding.etStoreNumber.setError("请输入店铺帐号");
            return;
        }
        if (TextUtils.isEmpty(userNum)) {
            mBinding.etMemberNumber.setError("请输入员工工号");
            return;
        }
        if (TextUtils.isEmpty(psw)) {
            mBinding.etStoreNumber.setError("请输入密码");
            return;
        }

        long storeId = Long.parseLong(storeNum) - 999L;
        long userId = Long.parseLong(userNum);

        OrganizationDao orgDao = DaoUtils.daoSession.getOrganizationDao();
        Organization org = orgDao.load(storeId);
        if (org == null) {
            mBinding.etStoreNumber.setError("该店铺帐号未注册");
            return;
        }
        UserDao userDao = DaoUtils.daoSession.getUserDao();
        User user = userDao.load(userId);
        if (user == null || user.getOrgId() != storeId) {
            mBinding.etMemberNumber.setError("该员工工号未注册");
            return;
        }
        if (!StringUtils.md5(psw).equals(user.getPassword())) {
            mBinding.etStoreNumber.setError("密码输入错误");
            return;
        }
        toast("成功");

        SPUtils.put(mContext, Constant.KEY_STORE_ID, storeId);
        SPUtils.put(mContext, Constant.KEY_USER_ID, userId);

        if (mBinding.cbRememberPassword.isChecked()) {
            SPUtils.put(mContext, Constant.KEY_USER_PASSWORD, user.getPassword());
        } else {
            SPUtils.put(mContext, Constant.KEY_USER_PASSWORD, "");
        }


    }

    public class Presenter {

        public void onClickLogin(View v) {
            login();
        }

        public void onClickRegister(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent, REQUEST_REGISTER);
        }
    }
}

