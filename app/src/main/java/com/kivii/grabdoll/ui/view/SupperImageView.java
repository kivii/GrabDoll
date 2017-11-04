package com.kivii.grabdoll.ui.view;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.bumptech.glide.request.RequestOptions;
import com.kivii.grabdoll.GlideApp;

import java.io.File;

public class SupperImageView extends AppCompatImageView {
    public SupperImageView(Context context) {
        super(context);
    }

    public SupperImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SupperImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImage(String path, int defaultRes) {
        Uri uri = null;
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists() && file.length() > 20) {
                uri = Uri.fromFile(file);
            }
        }

        if (uri != null) {
            setImageURI(uri);
        } else {
            setImageResource(defaultRes);
        }
    }

    public void loadImage(Object obj) {
        GlideApp.with(getContext()).load(obj).into(this);
    }

    public void loadImage(Object obj, int defaultRes) {
        GlideApp.with(getContext())
                .load(obj)
                .apply(RequestOptions
                .errorOf(defaultRes)
                .placeholder(defaultRes))
                .into(this);
    }
}
