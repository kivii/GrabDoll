package com.kivii.grabdoll.ui;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Toast toast;
    private Snackbar snackbar;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i : grantResults) {
            if(i != PermissionChecker.PERMISSION_GRANTED) {
                onPermsDenied();
                Toast.makeText(this, "必须开启权限才能使用此功能！", Toast.LENGTH_SHORT).show();
                onBackPressed();
                return;
            }
        }

        onPermsGranted();
    }

    protected boolean hasPerms(@NonNull String... perms) {
        // Always return true for SDK < M, let the system deal with the permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // DANGER ZONE!!! Changing this will break the library.
            return true;
        }

        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(this, perm)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    protected void requestPerms(String... perms) {
        ActivityCompat.requestPermissions(this, perms, 1);
    }

    /**
     * 请求权限成功，如果请求多个要全部成功才算成功
     */
    protected void onPermsGranted() {

    }

    /**
     * 请求权限失败
     */
    protected void onPermsDenied() {

    }

    protected void showProgress(String msg, boolean flag, boolean cancel) {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage("");
        progressDialog.setCancelable(flag);
        progressDialog.setCanceledOnTouchOutside(cancel);
        progressDialog.show();
    }

    protected void showProgress() {
        showProgress("", false, false);
    }

    protected void dismissProgress() {
        progressDialog.dismiss();
    }

    protected void snackbar(String msg) {
        if (snackbar == null) {
            snackbar = Snackbar.make(this.getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT);
        } else {
            snackbar.setDuration(Snackbar.LENGTH_SHORT);
            snackbar.setText(msg);
        }

        snackbar.show();
    }

    protected void snackbar(String msg, String actionStr, View.OnClickListener listener) {
        if (snackbar == null) {
            snackbar = Snackbar.make(this.getWindow().getDecorView(), msg, Snackbar.LENGTH_LONG);
        } else {
            snackbar.setDuration(Snackbar.LENGTH_LONG);
            snackbar.setText(msg);
        }

        snackbar.setAction(actionStr, listener);
        snackbar.show();
    }

    protected void toast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }

        toast.show();
    }
}
