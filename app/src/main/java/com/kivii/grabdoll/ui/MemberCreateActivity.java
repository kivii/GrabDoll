package com.kivii.grabdoll.ui;

import android.Manifest;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.Organization;
import com.kivii.grabdoll.core.bean.User;
import com.kivii.grabdoll.core.dao.OrganizationDao;
import com.kivii.grabdoll.core.dao.UserDao;
import com.kivii.grabdoll.databinding.ActivityMemberCreateBinding;
import com.kivii.grabdoll.util.Constant;
import com.kivii.grabdoll.util.DaoUtils;
import com.kivii.grabdoll.util.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MemberCreateActivity extends BaseActivity {
    private Context mContext;
    private ActivityMemberCreateBinding mBinding;
    private Organization mOrg;
    private User mUser;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_member_create);
        mContext = this;
        initView();
    }

    private void initView() {
        mBinding.setPresenter(new Presenter());
        OrganizationDao orgDao = DaoUtils.daoSession.getOrganizationDao();
        userDao = DaoUtils.daoSession.getUserDao();
        long orgId = SPUtils.getLong(Constant.KEY_STORE_ID);
        mOrg = orgDao.loadDeep(orgId);

        long userId = getIntent().getLongExtra(Constant.KEY_USER_ID, 0L);
        if (userId != 0L) {
            mUser = userDao.load(userId);
            mBinding.setUser(mUser);
        }

        setTitleName(mUser == null ? "添加成员" : "修改成员");

    }

    @Override
    protected void onPermsGranted(int requestCode) {
        switch (requestCode) {
            case 0:
                break;
            case 1:
                break;
        }
    }

    public class Presenter {
        public void onClickAvatar(View v) {
            new AlertDialog.Builder(mContext)
                    .setItems(new String[]{"相机拍照", "相册选择"}, (dialog, which) -> {
                        switch (which) {
                            case 0: {
                                String p1 = Manifest.permission.READ_EXTERNAL_STORAGE;
                                String p2 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                                String p3 = Manifest.permission.CAMERA;
                                requestPerms(0, p1, p2, p3);
                                break;
                            }
                            case 1: {
                                String p1 = Manifest.permission.READ_EXTERNAL_STORAGE;
                                String p2 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                                requestPerms(1, p1, p2);
                                break;
                            }
                        }
                    });


        }

        public void onClickBirthday(View v) {

        }

        public void onClickSave(View v) {

        }

        public String getBirthday(Date date) {
            if (date != null) {
                return new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).format(date);
            }
            return "保密";
        }
    }
}
