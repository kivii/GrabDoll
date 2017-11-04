package com.kivii.grabdoll.core.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils(){}

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTimeStr(Date date) {
        if (date == null) {
            return "";
        }
        Calendar currentCal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String template;
        if (currentCal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            if (currentCal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
                int day = currentCal.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
                if (day == 0) {
                    template = "HH:mm";
                } else if (day == 1) {
                    template = "昨天 HH:mm";
                } else if (day == 2) {
                    template = "前天 HH:mm";
                } else {
                    template = "MM/dd";
                }
            } else {
                template = "MM/dd";
            }
        } else {
            template = "yyyy/MM/dd";
        }
        return new SimpleDateFormat(template, Locale.CHINA).format(date);
    }

    /**
     * 复制
     *
     * @param content
     * @param context
     */
    public static void copy(CharSequence content, Context context) {
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText("text", content));
    }

    /**
     * 粘贴
     *
     * @param context
     * @return
     */
    public static CharSequence paste(Context context) {
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = cmb.getPrimaryClip();
        if (data != null) {
            return data.getItemAt(0).coerceToText(context);
        } else {
            return "";
        }
    }

    public static boolean isEmail(String input) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * 判断是否是手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(1[3456789])\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    private static final String[] SUFFIX_IMAGES = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
    public static boolean isImage(String path) {
        for (String s : SUFFIX_IMAGES) {
            if (path.endsWith(s)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 自动补全常用邮箱
     *
     * @param et 邦定的EditText
     */
    public static void setAutoCompleteEmail(final EditText et) {
        final ListPopupWindow listPopupWindow;
        final List<String> popupStrList;
        final ArrayAdapter popupAdapter;
        listPopupWindow = new ListPopupWindow(et.getContext());
        listPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupStrList = new ArrayList<>();
        popupAdapter = new ArrayAdapter<>(et.getContext(),
                android.R.layout.simple_spinner_dropdown_item, popupStrList);
        listPopupWindow.setAdapter(popupAdapter);
        listPopupWindow.setAnchorView(et);
        listPopupWindow.setModal(false);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if (s.length() > 2 && !str.contains("@")) {
                    listPopupWindow.show();
                    popupStrList.clear();
                    popupAdapter.notifyDataSetChanged();
                    popupStrList.add(str + "@qq.com");
                    popupStrList.add(str + "@163.com");
                    popupStrList.add(str + "@126.com");
                    popupStrList.add(str + "@139.com");
                    popupStrList.add(str + "@sina.com");
                    popupStrList.add(str + "@sina.cn");
                    popupAdapter.notifyDataSetChanged();
                } else {
                    listPopupWindow.dismiss();
                }
            }
        });

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et.setText(popupStrList.get(position));
                et.setSelection(et.getText().length());
            }
        });
    }

    public static CharSequence lightSearchStr(String searchText, String text) {
        if (!TextUtils.isEmpty(searchText) && text.contains(searchText)) {
            SpannableString ss = new SpannableString(text);
            int index = text.indexOf(searchText);
            while (index != -1) {
                int end = index + searchText.length();
                ss.setSpan(new ForegroundColorSpan(Color.parseColor("#40a8ff")), index, end,
                        SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
                index = text.indexOf(searchText, end);
            }

            return ss;
        } else {
            return text;
        }
    }
}
