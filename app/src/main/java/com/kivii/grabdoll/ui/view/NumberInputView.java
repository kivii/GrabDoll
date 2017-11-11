package com.kivii.grabdoll.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kivii.grabdoll.R;

public class NumberInputView extends LinearLayout {
    private int minValue;
    private int maxValue;
    private int step;
    private int value;
    private TextView tvNumber;

    public NumberInputView(Context context) {
        this(context, null);
    }

    public NumberInputView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.view_input_number, this);
        TextView icPlus = view.findViewById(R.id.ic_plus);
        TextView icMinus = view.findViewById(R.id.ic_minus);
        tvNumber = view.findViewById(R.id.tv_number);

        minValue = -10000;
        maxValue = 10000;
        step = 1;
        setValue(0);

        icPlus.setOnTouchListener((v, event) -> onTouchIcon(true, event));
        icMinus.setOnTouchListener((v, event) -> onTouchIcon(false, event));
    }

    private void add(boolean isAdd) {
        value += isAdd ? step : -step;
        setValue(value);
    }

    private long recordMillis;

    private boolean onTouchIcon(boolean isAdd, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                recordMillis = System.currentTimeMillis();
                startTimer(isAdd);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (System.currentTimeMillis() - recordMillis <= 1000) {
                    add(isAdd);
                }
                recordMillis = 0L;
                break;
        }
        return true;
    }

    private void startTimer(boolean isAdd) {
        postDelayed(() -> {
            if (recordMillis != 0L) {
                if (System.currentTimeMillis() - recordMillis > 1000) {
                    add(isAdd);
                }
                startTimer(isAdd);
            }
        }, 70);
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = Math.max(minValue, Math.min(maxValue, value));
        if (tvNumber != null) tvNumber.setText(String.valueOf(this.value));
    }
}
