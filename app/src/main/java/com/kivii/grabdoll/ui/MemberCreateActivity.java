package com.kivii.grabdoll.ui;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.kivii.grabdoll.R;
import com.kivii.grabdoll.core.bean.Organization;
import com.kivii.grabdoll.core.bean.User;
import com.kivii.grabdoll.core.dao.OrganizationDao;
import com.kivii.grabdoll.core.dao.UserDao;
import com.kivii.grabdoll.databinding.ActivityMemberCreateBinding;
import com.kivii.grabdoll.util.CameraUtils;
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

    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_ALBUMS = 2;
    private static final int REQUEST_CLIP = 3;
    private static final int REQUEST_CONTACT = 4;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;

        switch (requestCode) {
            case REQUEST_CAMERA:
                CameraUtils.clipImage(this, REQUEST_CLIP, data.getData());
                break;
            case REQUEST_ALBUMS:
                CameraUtils.clipImage(this, REQUEST_CLIP, data.getData());
                break;
            case REQUEST_CLIP:
                if (data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    if (bitmap != null) {
                        mBinding.ivAvatar.setImageBitmap(bitmap);
                    }
                }
                break;
            case REQUEST_CONTACT:
                ContentResolver cr = getContentResolver();
                Uri uri = data.getData();
                if (uri != null) {
                    Cursor cursor = cr.query(uri,
                            new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID},
                            null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        String name = cursor.getString(0);
                        if (!TextUtils.isEmpty(name)) mBinding.etName.setText(name);

                        String id = cursor.getString(1);

                        Cursor cursor1 = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                                null, null);

                        if (cursor1 != null && cursor1.getCount() > 0) {
                            cursor1.moveToFirst();
                            String number = cursor1.getString(0);
                            if (!TextUtils.isEmpty(number)) mBinding.etMobile.setText(number);
                            cursor1.close();
                        }

                        cursor.close();
                    }
                }

                break;
            default:
                break;
        }
    }

    @Override
    protected void onPermsGranted(int requestCode) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                CameraUtils.sysCamera(this, REQUEST_CAMERA);
                break;
            case REQUEST_ALBUMS:
                CameraUtils.systemAlbums(this, REQUEST_ALBUMS);
                break;
            case REQUEST_CONTACT:
                startActivityForResult(
                        new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI),
                        REQUEST_CONTACT);
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
                                requestPerms(REQUEST_CAMERA, p1, p2, p3);
                                break;
                            }
                            case 1: {
                                String p1 = Manifest.permission.READ_EXTERNAL_STORAGE;
                                String p2 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                                requestPerms(REQUEST_ALBUMS, p1, p2);
                                break;
                            }
                        }
                    }).show();


        }

        public void onClickBirthday(View v) {

        }

        public void onClickSave(View v) {

        }

        public void onClickImport(View v) {
            requestPerms(REQUEST_CONTACT, Manifest.permission.READ_CONTACTS);
        }

        public String getBirthday(Date date) {
            if (date != null) {
                return new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).format(date);
            }
            return "保密";
        }
    }
}
