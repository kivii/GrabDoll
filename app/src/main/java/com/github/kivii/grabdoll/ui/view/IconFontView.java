package com.github.kivii.grabdoll.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class IconFontView extends AppCompatTextView {
    public IconFontView(Context context) {
        this(context, null);
    }

    public IconFontView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconFontView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/iconfont.ttf"));
    }


}
