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

import java.util.List;

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

        long storeId = SPUtils.getLong(Constant.KEY_STORE_ID);
        String userNum = SPUtils.getString(Constant.KEY_USER_NUMBER);
        String psw = SPUtils.getString(Constant.KEY_USER_PASSWORD);

        if (storeId != 0L) {
            mBinding.etStoreNumber.setText(String.valueOf(storeId + 999));
        }
        mBinding.etMemberNumber.setText(userNum);
        mBinding.etPassword.setText(psw);

        startLogin(userNum, psw, storeId, false);
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
        String num = data.getStringExtra(Constant.KEY_USER_NUMBER);
        if (storeId == 0L || userId == 0L || TextUtils.isEmpty(psw) || TextUtils.isEmpty(num)) {
            toast("注册失败");
            return;
        }
        mBinding.etStoreNumber.setText(String.valueOf(storeId + 999));
        mBinding.etMemberNumber.setText(num);
        mBinding.etPassword.setText(psw);

        new AlertDialog.Builder(mContext)
                .setTitle("注册成功")
                .setMessage("您的店铺帐号为 " + (storeId + 999) +
                        "\n您的工号为 " + num + "\n请记住了")
                .setPositiveButton("确定", null)
                .show();
    }

    private void login() {
        String storeNum = mBinding.etStoreNumber.getText().toString();
        String userNum = mBinding.etMemberNumber.getText().toString();
        String psw = mBinding.etPassword.getText().toString();

        if (TextUtils.isEmpty(storeNum)) {
            toast("请输入店铺帐号");
            return;
        }
        if (TextUtils.isEmpty(userNum)) {
            toast("请输入员工工号");
            return;
        }
        if (TextUtils.isEmpty(psw)) {
            toast("请输入密码");
            return;
        }
        long storeId = Long.parseLong(storeNum) - 999L;

        startLogin(userNum, psw, storeId, true);
    }

    private void startLogin(String userNum, String psw, long storeId, boolean showError) {
        OrganizationDao orgDao = DaoUtils.daoSession.getOrganizationDao();
        Organization org = orgDao.load(storeId);
        if (org == null) {
            if (showError) toast("该店铺帐号未注册");
            return;
        }
        UserDao userDao = DaoUtils.daoSession.getUserDao();
        User user = null;
        List<User> users = userDao.queryBuilder()
                .where(UserDao.Properties.OrgId.eq(storeId), UserDao.Properties.Number.eq(userNum))
                .list();
        if (!users.isEmpty()) {
            user = users.get(0);
        }

        if (user == null) {
            if (showError) toast("该员工工号未注册");
            return;
        }
        if (!StringUtils.md5(psw).equals(user.getPassword())) {
            if (showError) toast("密码输入错误");
            return;
        }
        SPUtils.put(Constant.KEY_STORE_ID, storeId);
        SPUtils.put(Constant.KEY_USER_NUMBER, userNum);
        SPUtils.put(Constant.KEY_USER_ID, user.getId());

        if (mBinding.cbRememberPassword.isChecked()) {
            SPUtils.put(Constant.KEY_USER_PASSWORD, psw);
        } else {
            SPUtils.put(Constant.KEY_USER_PASSWORD, "");
        }

        toMainActivity();
    }

    private void toMainActivity() {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    public class Presenter {

        public void onClickSetting(View v) {
            startActivity(new Intent(mContext, MemberCreateActivity.class));
        }

        public void onClickLogin(View v) {
            login();
        }

        public void onClickRegister(View v) {
            Intent intent = new Intent(mContext, RegisterActivity.class);
            startActivityForResult(intent, REQUEST_REGISTER);
        }
    }
}

