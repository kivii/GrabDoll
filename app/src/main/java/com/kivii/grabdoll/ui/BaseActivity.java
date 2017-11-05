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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kivii.grabdoll.R;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Toast toast;
    private Snackbar snackbar;

    protected void setTitleName(String name) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        TextView title = findViewById(R.id.title_name);
        if (title != null) {
            title.setText(name);
        }

        TextView back = findViewById(R.id.title_ic_back);
        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    protected void setRightText(String text, View.OnClickListener lis) {
        TextView rightText = findViewById(R.id.title_right);
        if (rightText != null) {
            rightText.setVisibility(View.VISIBLE);
            rightText.setText(text);
            rightText.setOnClickListener(lis);
        }
    }

    protected void showRightText() {
        TextView rightText = findViewById(R.id.title_right);
        if (rightText != null) {
            rightText.setVisibility(View.VISIBLE);
        }
    }

    protected void hideRightText() {
        TextView rightText = findViewById(R.id.title_right);
        if (rightText != null) {
            rightText.setVisibility(View.GONE);
        }
    }

    protected void setRightIc(int icRes, View.OnClickListener lis) {
        TextView icRight = findViewById(R.id.title_ic_right);
        if (icRight != null) {
            icRight.setVisibility(View.VISIBLE);
            icRight.setText(icRes);
            icRight.setOnClickListener(lis);
        }
    }

    protected void showRightIc() {
        TextView rightIc = findViewById(R.id.title_ic_right);
        if (rightIc != null) {
            rightIc.setVisibility(View.VISIBLE);
        }
    }

    protected void hideRightIc() {
        TextView rightIc = findViewById(R.id.title_ic_right);
        if (rightIc != null) {
            rightIc.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i : grantResults) {
            if(i != PermissionChecker.PERMISSION_GRANTED) {
                onPermsDenied();
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

    protected void showProgress(String msg, boolean cancelable) {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(cancelable);
        progressDialog.setCanceledOnTouchOutside(cancelable);
        progressDialog.show();
    }

    protected void showProgress(String msg) {
        showProgress(msg, false);
    }

    protected void showProgress() {
        showProgress("");
    }

    protected void dismissProgress() {
        progressDialog.dismiss();
    }

    protected void snackBar(String msg) {
        if (snackbar == null) {
            snackbar = Snackbar.make(this.getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT);
        } else {
            snackbar.setDuration(Snackbar.LENGTH_SHORT);
            snackbar.setText(msg);
        }

        snackbar.show();
    }

    protected void snackBar(String msg, String actionStr, View.OnClickListener listener) {
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
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }
}
