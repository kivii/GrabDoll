package com.kivii.grabdoll.ui.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {
    private Paint dividerPaint;
    private int dividerHeight;

    public SimpleItemDecoration() {
        this(Color.parseColor("#D0D0D0"), 1);
    }

    public SimpleItemDecoration(int color, int height) {
        dividerPaint = new Paint();
        dividerPaint.setColor(color);
        dividerHeight = height;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = 1;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = top + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}
