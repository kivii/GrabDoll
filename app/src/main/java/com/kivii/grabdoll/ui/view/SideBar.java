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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SideBar extends View {
    public static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private TextPaint textPaint;
    private List<String> letterList;
    private boolean isTouch = false;
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int singleHeight = height / LETTERS.length;

        final float x = (width - textPaint.measureText(LETTERS[0])) / 2;
        for (int i = 0; i < LETTERS.length; i++) {
            if (isTouch && letterList != null) {
                if (letterList.contains(LETTERS[i])) {
                    textPaint.setColor(Color.BLACK);
                } else {
                    textPaint.setColor(Color.GRAY);
                }
            } else {
                textPaint.setColor(Color.GRAY);
            }
            float y = singleHeight * (i + 1);
            canvas.drawText(LETTERS[i], x, y, textPaint);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                invalidate();
                setBackgroundColor(Color.parseColor("#20000000"));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isTouch = false;
                invalidate();
                setBackgroundColor(Color.TRANSPARENT);
                if(floatText != null) {
                    floatText.setVisibility(View.GONE);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int y = (int) event.getY();
                final int singleHeight = getHeight() / LETTERS.length;
                int m = y / singleHeight;
                m = m < 0 ? 0 : m;
                m = m > LETTERS.length - 1 ? LETTERS.length - 1 : m;
                String letter = LETTERS[m];

                if(letterList != null && letterList.contains(letter)) {
                    if(onLetterChangeListener != null) {
                        onLetterChangeListener.onLetterChange(letter);
                    }

                    if(floatText != null) {
                        floatText.setVisibility(View.VISIBLE);
                        floatText.setText(letter);
                    }
                }
                break;
        }

        return true;
    }

    public static List<String> getLetters() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(LETTERS).subList(0, LETTERS.length - 1));
        return list;
    }

    public void setLetterList(List<String> letterList) {
        this.letterList = letterList;
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
