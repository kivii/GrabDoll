package com.kivii.grabdoll.ui.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.kivii.grabdoll.util.DensityUtils;

import java.util.List;

public class SideBar extends View {
    private TextPaint textPaint;
    private List<String> letterList;
    private OnLetterChangeListener onLetterChangeListener;
    private TextView floatText;

    public SideBar(Context context) {
        super(context);
        init(context);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(DensityUtils.dp2px(context, 12));
        textPaint.setColor(Color.parseColor("#80404042"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (letterList == null || letterList.isEmpty()) return;
        final int size = letterList.size();

        final int width = getWidth();
        final int height = getHeight();
        final int singleHeight = height / 27;
        final int top = (27 - size) / 2;

        final float x = (width - textPaint.measureText(letterList.get(0))) / 2;
        for (int i = 0; i < size; i++) {
            float y = singleHeight * (i + 1) + top;
            canvas.drawText(letterList.get(i), x, y, textPaint);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(floatText != null) {
                    floatText.setVisibility(View.GONE);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (letterList == null || letterList.isEmpty()) break;

                int y = (int) event.getY();
                final int singleHeight = getHeight() / 27;
                final int size = letterList.size();
                final int top = (27 - size) / 2;

                if (y < top || y > (singleHeight * size + top)) break;

                int m = (y - top) / singleHeight;
                m = Math.min(Math.max(0, m), size);
                String letter = letterList.get(m);

                if(onLetterChangeListener != null) {
                    onLetterChangeListener.onLetterChange(letter);
                }

                if(floatText != null) {
                    floatText.setVisibility(View.VISIBLE);
                    floatText.setText(letter);
                }
                break;
        }

        return true;
    }

    public void setLetterList(List<String> letterList) {
        this.letterList = letterList;
        invalidate();
    }

    public void setFloatText(TextView tv) {
        this.floatText = tv;
    }

    public void setOnLetterChangeListener(OnLetterChangeListener onLetterChangeListener) {
        this.onLetterChangeListener = onLetterChangeListener;
    }

    public interface OnLetterChangeListener {
        void onLetterChange(String letter);
    }
}
