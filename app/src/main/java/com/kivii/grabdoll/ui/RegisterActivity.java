package com.kivii.grabdoll.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.Organization;
import com.kivii.grabdoll.core.bean.User;
import com.kivii.grabdoll.core.dao.OrganizationDao;
import com.kivii.grabdoll.core.dao.UserDao;
import com.kivii.grabdoll.databinding.ActivityRegisterBinding;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.StringUtils;

import java.util.Date;

public class RegisterActivity extends BaseActivity {
    private Context mContext;
    private ActivityRegisterBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        setTitleName(getString(R.string.text_register));
        initView();
    }

    @Override
    public void onBackPressed() {
        if (mBinding.getPage() == 1) {
            setPage(0);
        } else {
            super.onBackPressed();
        }
    }

    private void initView() {
        mBinding.setPresenter(new Presenter());
    }

    private void changeBtnState() {
        if (mBinding.getPage() == 0) {
            mBinding.setClickable(mBinding.etStoreName.length() > 0);
        } else {
            int len0 = mBinding.etManagerName.length();
            int len1 = mBinding.etPassword.length();
            int len2 = mBinding.etConfirm.length();
            mBinding.setClickable(len0 > 0 && len1 > 0 && len1 == len2);
        }
    }

    private void setPage(int page) {
        if (page == 1) {
            mBinding.page0.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
            mBinding.page1.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
        } else {
            mBinding.page0.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
            mBinding.page1.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_out));
        }

        mBinding.setPage(page);
        changeBtnState();
    }

    private void onClickComplete() {
        String password = mBinding.etPassword.getText().toString();
        String confirm = mBinding.etConfirm.getText().toString();
        if (!password.equals(confirm)) {
            mBinding.etConfirm.setError("密码输入不一致");
            return;
        }

        String storeName = mBinding.etStoreName.getText().toString();
        String storeAddress = mBinding.etStoreAddress.getText().toString();
        String storeIntro = mBinding.etStoreIntro.getText().toString();
        String managerName = mBinding.etManagerName.getText().toString();
        String managerMobile = mBinding.etManagerMobile.getText().toString();

        Organization org = new Organization();
        org.setName(storeName);
        org.setAddress(storeAddress);
        org.setDescribe(storeIntro);
        org.setAddTime(new Date());
        OrganizationDao orgDao = DaoUtils.daoSession.getOrganizationDao();
        long orgId = orgDao.insert(org);

        User user = new User();
        user.setName(managerName);
        user.setMobile(managerMobile);
        user.setLevel(1);
        user.setNumber("001");
        user.setPassword(StringUtils.md5(password));
        user.setAddTime(new Date());
        user.setOrgId(orgId);
        UserDao userDao = DaoUtils.daoSession.getUserDao();
        long userId = userDao.insert(user);

        Intent intent = getIntent();
        intent.putExtra(Constant.KEY_STORE_ID, orgId);
        intent.putExtra(Constant.KEY_USER_ID, userId);
        intent.putExtra(Constant.KEY_USER_NUMBER, user.getNumber());
        intent.putExtra(Constant.KEY_USER_PASSWORD, password);
        setResult(RESULT_OK, intent);
        finish();
    }

    public class Presenter {
        public void onClickBtn(View v) {
            if (!mBinding.getClickable()) {
                return;
            }

            if (mBinding.getPage() == 0) {
                setPage(1);
            } else {
                onClickComplete();
            }
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            changeBtnState();
        }
    }
}
