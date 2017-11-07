package com.kivii.grabdoll.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 打开或关闭软键盘
 */
public class KeyBoardUtils {
    private KeyBoardUtils(){}

    /**
     * 打开软键盘
     *
     * @param et 输入框
     */
    public static void openKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(et, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    /**
     * 关闭软键盘
     *
     * @param et 输入框
     */
    public static void closeKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public static void initKeyboardHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int screenHeight = activity.getWindow().getDecorView().getRootView().getHeight();
        int softHeight = screenHeight - rect.bottom;

        if (softHeight > 0) {
            SPUtils.put("softHeight", softHeight);
        }
    }

    public static int getSoftHeight(Context context) {
        return SPUtils.getInt("softHeight");
    }
}
