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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {

    /**
     * 去掉html标签
     *
     * @param htmlStr
     * @return
     */
    public static String clearHtmlTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
        String regEx_other = "<a>\\s*|\t|\r|\n</a>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");

        Pattern p_other = Pattern.compile(regEx_other, Pattern.CASE_INSENSITIVE);
        Matcher m_other = p_other.matcher(htmlStr);
        htmlStr = m_other.replaceAll("");

        Pattern p_space = Pattern.compile("[ ]");
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll("");

        htmlStr = htmlStr.replaceAll("&emsp;", "");
        htmlStr = htmlStr.replaceAll("&nbsp;", "");

        return htmlStr.trim();
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String date2String(Date date, String format) {
        return new SimpleDateFormat(format, Locale.CHINA).format(date);
    }

    /**
     * 字符串转日期
     */
    public static Date string2Date(String str) {
        Date date = null;
        String pattern;

        if (str.equals("")) {
            return null;
        }

        try {
            date = parse("yyyy-MM-dd HH:mm:ss", str);
        } catch (ParseException e) {
            e.printStackTrace();
            try {
                date = parse("yyyy/MM/dd HH:mm:ss", str);
            } catch (ParseException e1) {
                e1.printStackTrace();
                try {
                    date = parse("yyyy-MM-dd", str);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                    try {
                        date = parse("yyyy-M-d HH:mm:ss", str);
                    } catch (ParseException e3) {
                        e3.printStackTrace();
                        try {
                            date = parse("yyyy-MM-dd HH:mm", str);
                        } catch (ParseException e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            }
        }

        return date;
    }

    public static Date parse(String template, String dateStr) throws ParseException {
        return new SimpleDateFormat(template, Locale.CHINA).parse(dateStr);
    }


    public static String listToString(List list) {
        String str = "";
        for (int i = 0, len = list.size(); i < len; i++) {
            if (i != 0) {
                str += ",";
            }
            str += list.get(i).toString();
        }

        return str;
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
        return date2String(date, template);
    }

    public static String getDefaultDateStr(Date date) {
        if (date == null) {
            return "";
        }
        Calendar currentCal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String template;
        if (currentCal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            template = "MM/dd HH:mm";
        } else {
            template = "yyyy/MM/dd HH:mm";
        }
        return date2String(date, template);
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
     * 目前有的号段：
     * 移动 139 138 137 136 135 134 147 150 151 152 157 158 159 178 182 183 184 187 188, 198 148 144(13位)
     * 联通 130 131 132 155 156 185 186 145 176, 166 146
     * 电信 133 153 177 173 180 181 189, 199 1740 1741 141(13位)
     * 虚拟运营商 170 171
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

    public static CharSequence changeSearchStr(String searchText, String text) {
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

    /**
     * 通过CmyIP获取获取外网IP地址  需在异步线程中访问
     *
     * @return 外网IP
     */
    public static String getOuterNetFormCmyIP() {
        String response = getOuterNetIp("http://www.cmyip.com/");
        Pattern pattern = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }


    /**
     * 获取获取外网外网地址  需在异步线程中访问
     *
     * @param ipaddr 提供外网服务的服务器ip地址
     * @return 外网IP
     */
    private static String getOuterNetIp(String ipaddr) {
        URL infoUrl = null;
        InputStream inStream = null;
        try {
            infoUrl = new URL(ipaddr);
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line).append("\n");
                inStream.close();
                return strber.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isIdCardNumber(String number) {
        if (number.length() != 18) {
            return false;
        }

        String str = "[0-9]{17}[0-9 X]$";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(number.toUpperCase());
        return matcher.matches();
    }
}
