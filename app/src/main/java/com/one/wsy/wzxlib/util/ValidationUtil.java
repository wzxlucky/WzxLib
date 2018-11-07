package com.one.wsy.wzxlib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：文本验证工具类
 * 名称: ValidationUtil
 * 版本: 1.0
 * 日期: 2018/4/12 14:26
 *
 * @author wsy
 */
public class ValidationUtil {

    private final static String TAG = "ValidationUtil";
    /**
     * 护照表达式
     */
    private static final Pattern PASSPORT_PATTERN = Pattern.compile("^1[45][0-9]{7}|([P|p|S|s]\\d{7})|([S|s|G|g]\\d{8})|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8})|([H|h|M|m]\\d{8,10})$");

    /**
     * 邮箱表达式
     */
    private final static Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    /**
     * 手机号表达式
     */
    private final static Pattern PHONE_PATTERN = Pattern.compile("^(13|15|17|18)\\d{9}$");

    /**
     * 银行卡号表达式
     */
    private final static Pattern BANKNO_PATTERN = Pattern.compile("^[0-9]{16,19}$");

    /**
     * 座机号码表达式
     */
    private final static Pattern PLANE_PATTERN = Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$");

    /**
     * 非零表达式
     */
    private final static Pattern NOTZERO_PATTERN = Pattern.compile("^\\+?[1-9][0-9]*$");

    /**
     * 数字表达式
     */
    private final static Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    /**
     * 大写字母表达式
     */
    private final static Pattern UPCHAR_PATTERN = Pattern.compile("^[A-Z]+$");

    /**
     * 小写字母表达式
     */
    private final static Pattern LOWCHAR_PATTERN = Pattern.compile("^[a-z]+$");

    /**
     * 大小写字母表达式
     */
    private final static Pattern LETTER_PATTERN = Pattern.compile("^[A-Za-z]+$");

    /**
     * 中文汉字表达式
     */
    private final static Pattern CHINESE_PATTERN = Pattern.compile("^[\u4e00-\u9fa5],{0,}$");

    /**
     * 条形码表达式
     */
    private final static Pattern ONECODE_PATTERN = Pattern.compile("^(([0-9])|([0-9])|([0-9]))\\d{10}$");

    /**
     * 邮政编码表达式
     */
    private final static Pattern POSTALCODE_PATTERN = Pattern.compile("([0-9]{3})+.([0-9]{4})+");

    /**
     * IP地址表达式
     */
    private final static Pattern IPADDRESS_PATTERN = Pattern.compile("[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))");

    /**
     * URL地址表达式
     */
    private final static Pattern URL_PATTERN = Pattern.compile("(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?");

    /**
     * 用户名表达式
     */
    private final static Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]{1}[A-Za-z0-9_.-]{3,31}");

    /**
     * 真实姓名表达式
     */
    private final static Pattern REALNEM_PATTERN = Pattern.compile("[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*");

    /**
     * 匹配HTML标签,通过下面的表达式可以匹配出HTML中的标签属性
     */
    private final static Pattern HTML_PATTER = Pattern.compile("<\\\\/?\\\\w+((\\\\s+\\\\w+(\\\\s*=\\\\s*(?:\".*?\"|'.*?'|[\\\\^'\">\\\\s]+))?)+\\\\s*|\\\\s*)\\\\/?>");

    /**
     * 抽取注释,如果你需要移除HMTL中的注释，可以使用如下的表达式
     */
    private final static Pattern NOTES_PATTER = Pattern.compile("<!--(.*?)-->");

    /**
     * 查找CSS属性,通过下面的表达式，可以搜索到相匹配的CSS属性
     */
    private final static Pattern CSS_PATTER = Pattern.compile("^\\\\s*[a-zA-Z\\\\-]+\\\\s*[:]{1}\\\\s[a-zA-Z0-9\\\\s.#]+[;]{1}");

    /**
     * 提取页面超链接,提取html中的超链接
     */
    private final static Pattern HYPERLINK_PATTER = Pattern.compile("(<a\\\\s*(?!.*\\\\brel=)[^>]*)(href=\"https?:\\\\/\\\\/)((?!(?:(?:www\\\\.)?'.implode('|(?:www\\\\.)?', $follow_list).'))[^\"]+)\"((?!.*\\\\brel=)[^>]*)(?:[^>]*)>");

    /**
     * 提取网页图片,假若你想提取网页中所有图片信息，可以利用下面的表达式
     */
    private final static Pattern IMAGE_PATTER = Pattern.compile("\\\\< *[img][^\\\\\\\\>]*[src] *= *[\\\\\"\\\\']{0,1}([^\\\\\"\\\\'\\\\ >]*)");

    /**
     * 提取Color Hex Codes,有时需要抽取网页中的颜色代码，可以使用下面的表达式
     */
    private final static Pattern COLOR_PATTER = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

    /**
     * 文件路径及扩展名校验,验证windows下文件路径和扩展名（下面的例子中为.txt文件）
     */
    private final static Pattern ROUTE_PATTER = Pattern.compile("^([a-zA-Z]\\\\:|\\\\\\\\)\\\\\\\\([^\\\\\\\\]+\\\\\\\\)*[^\\\\/:*?\"<>|]+\\\\.txt(l)?$");

    /**
     * 字母、数字、字符任意两种组合
     */
    private final static Pattern PWD_PATTER = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[A-Za-z0-9\\W]{1,}$");


    /**
     * 验证密码
     */

    public static boolean isPwdRight(String str) {
        return PWD_PATTER.matcher(str).matches();
    }

    /**
     * 验证是否为空串 (包括空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串,返回true)
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }


    /**
     * 是否不为空
     *
     * @param s
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !"".equals(s.trim());
    }

    /**
     * 验证非零正整数
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isNotZero(String str) {
        return NOTZERO_PATTERN.matcher(str).matches();
    }


    /**
     * 验证是数字
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isNumber(String str) {
        return NUMBER_PATTERN.matcher(str).matches();
    }


    /**
     * 验证是大写字母
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isUpChar(String str) {
        return UPCHAR_PATTERN.matcher(str).matches();
    }


    /**
     * 验证是小写字母
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isLowChar(String str) {
        return LOWCHAR_PATTERN.matcher(str).matches();
    }


    /**
     * 验证是英文字母
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isLetter(String str) {
        return LETTER_PATTERN.matcher(str).matches();
    }


    /**
     * 验证输入汉字
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isChinese(String str) {
        return CHINESE_PATTERN.matcher(str).matches();
    }


    /**
     * 验证真实姓名
     *
     * @param str 验证字符
     * @return
     */
    public static boolean isRealName(String str) {
        return REALNEM_PATTERN.matcher(str).matches();
    }


    /**
     * 验证是否是条形码
     *
     * @param oneCode 条形码
     * @return boolean
     */
    public static boolean isOneCode(String oneCode) {
        return ONECODE_PATTERN.matcher(oneCode).matches();
    }


    /**
     * 是否含有特殊符号
     *
     * @param str 待验证的字符串
     * @return 是否含有特殊符号
     */
    public static boolean hasSpecialCharacter(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    /**
     * 验证邮箱是否正确
     *
     * @param email 邮箱地址
     * @return boolean
     */
    public static boolean isEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 验证护照是否正确
     *
     * @param passport 护照编号
     * @return boolean
     */
    public static boolean isPassPort(String passport) {
        return PASSPORT_PATTERN.matcher(passport).matches();
    }


    /**
     * 验证手机号是否正确
     *
     * @param phone 手机号码
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }


    /**
     * 验证座机号码是否正确
     *
     * @param plane 座机号码
     * @return boolean
     */
    public static boolean isPlane(String plane) {
        return PLANE_PATTERN.matcher(plane).matches();
    }


    /**
     * 验证邮政编码是否正确
     *
     * @param postalcode 邮政编码
     * @return boolean
     */
    public static boolean isPostalCode(String postalcode) {
        return POSTALCODE_PATTERN.matcher(postalcode).matches();
    }


    /**
     * 验证IP地址是否正确
     *
     * @param ipaddress IP地址
     * @return boolean
     */
    public static boolean isIpAddress(String ipaddress) {
        return IPADDRESS_PATTERN.matcher(ipaddress).matches();
    }


    /**
     * 验证URL地址是否正确
     *
     * @param url 地址
     * @return boolean
     */
    public static boolean isURL(String url) {
        return URL_PATTERN.matcher(url).matches();
    }


    /**
     * 验证是否是正整数
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isInteger(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 验证是否是小数
     *
     * @param paramString 验证字符
     * @return boolean
     */
    public static boolean isPoint(String paramString) {
        if (paramString.indexOf(".") > 0) {
            if (paramString.substring(paramString.indexOf(".")).length() > 3) {
                return false;
            }
        }
        return true;
    }


    /**
     * 验证是否银行卡号
     *
     * @param bankNo 银行卡号
     * @return
     */
    public static boolean isBankNo(String bankNo) {
        //替换空格
        bankNo = bankNo.replaceAll(" ", "");
        //银行卡号可为12位数字
        if (12 == bankNo.length()) {
            return true;
        }
        //银行卡号可为16-19位数字
        return BANKNO_PATTERN.matcher(bankNo).matches();
    }

    /**
     * 验证身份证号码是否正确
     *
     * @param IDCardNo 身份证号码
     * @return boolean
     */
    public static boolean isIDCard(String IDCardNo) {
        //记录错误信息	
        String errmsg = "";
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";

        //================ 身份证号码的长度 15位或18位 ================
        if (IDCardNo.length() != 15 && IDCardNo.length() != 18) {
            errmsg = "身份证号码长度应该为15位或18位!";
            Log.e(TAG, errmsg);
            return false;
        }

        //================ 数字 除最后以为都为数字 ================
        if (IDCardNo.length() == 18) {
            Ai = IDCardNo.substring(0, 17);
        } else if (IDCardNo.length() == 15) {
            Ai = IDCardNo.substring(0, 6) + "19" + IDCardNo.substring(6, 15);
        }
        if (isNumber(Ai) == false) {
            errmsg = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字";
            Log.e(TAG, errmsg);
            return false;
        }

        //================ 出生年月是否有效 ================
        //年份
        String strYear = Ai.substring(6, 10);
        //月份
        String strMonth = Ai.substring(10, 12);
        //日
        String strDay = Ai.substring(12, 14);
        if (getDateIsTrue(strYear, strMonth, strDay) == false) {
            errmsg = "身份证生日无效";
            Log.e(TAG, errmsg);
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errmsg = "身份证生日不在有效范围";
                Log.e(TAG, errmsg);
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errmsg = "身份证生日不在有效范围";
            Log.e(TAG, errmsg + e.getMessage());
            return false;
        } catch (ParseException e1) {
            e1.printStackTrace();
            errmsg = "身份证生日不在有效范围";
            Log.e(TAG, errmsg + e1.getMessage());
            return false;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errmsg = "身份证月份无效";
            Log.e(TAG, errmsg);
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errmsg = "身份证日期无效";
            Log.e(TAG, errmsg);
            return false;
        }

        //================ 地区码时候有效 ================
        Hashtable hashtable = getAreaCodeAll();
        if (hashtable.get(Ai.substring(0, 2)) == null) {
            errmsg = "身份证地区编码错误";
            Log.e(TAG, errmsg);
            return false;
        }

        //================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;
        if (IDCardNo.length() == 18) {
            if (Ai.equals(IDCardNo) == false) {
                errmsg = "身份证无效，不是合法的身份证号码";
                Log.e(TAG, errmsg);
                return false;
            }
        } else {
            return true;
        }
        return true;
    }


    public static Hashtable getAreaCodeAll() {
        Hashtable hashtableAreaCode = new Hashtable();
        hashtableAreaCode.put("11", "北京");
        hashtableAreaCode.put("12", "天津");
        hashtableAreaCode.put("13", "河北");
        hashtableAreaCode.put("14", "山西");
        hashtableAreaCode.put("15", "内蒙古");
        hashtableAreaCode.put("21", "辽宁");
        hashtableAreaCode.put("22", "吉林");
        hashtableAreaCode.put("23", "黑龙江");
        hashtableAreaCode.put("31", "上海");
        hashtableAreaCode.put("32", "江苏");
        hashtableAreaCode.put("33", "浙江");
        hashtableAreaCode.put("34", "安徽");
        hashtableAreaCode.put("35", "福建");
        hashtableAreaCode.put("36", "江西");
        hashtableAreaCode.put("37", "山东");
        hashtableAreaCode.put("41", "河南");
        hashtableAreaCode.put("42", "湖北");
        hashtableAreaCode.put("43", "湖南");
        hashtableAreaCode.put("44", "广东");
        hashtableAreaCode.put("45", "广西");
        hashtableAreaCode.put("46", "海南");
        hashtableAreaCode.put("50", "重庆");
        hashtableAreaCode.put("51", "四川");
        hashtableAreaCode.put("52", "贵州");
        hashtableAreaCode.put("53", "云南");
        hashtableAreaCode.put("54", "西藏");
        hashtableAreaCode.put("61", "陕西");
        hashtableAreaCode.put("62", "甘肃");
        hashtableAreaCode.put("63", "青海");
        hashtableAreaCode.put("64", "宁夏");
        hashtableAreaCode.put("65", "新疆");
        hashtableAreaCode.put("71", "台湾");
        hashtableAreaCode.put("81", "香港");
        hashtableAreaCode.put("82", "澳门");
        hashtableAreaCode.put("91", "国外");
        return hashtableAreaCode;
    }

    /**
     * 检查日期是否有效
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return boolean
     */
    public static boolean getDateIsTrue(String year, String month, String day) {
        try {
            String data = year + month + day;
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
            simpledateformat.setLenient(false);
            simpledateformat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage().toString());
            return false;
        }
        return true;
    }

    /**
     * 判断是否有特殊字符
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isPeculiarStr(String str) {
        boolean flag = false;
        String regEx = "[^0-9a-zA-Z\u4e00-\u9fa5]+";
        if (str.length() != (str.replaceAll(regEx, "").length())) {
            flag = true;
        }
        return flag;
    }


    /**
     * 判断是否为用户名账号(规则如下：用户名由下划线或字母开头，由数字、字母、下划线、点、减号组成的4-32位字符)
     *
     * @param username 用户名
     * @return boolean
     */
    public static boolean isUserName(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    /**
     * 获取字符串中文字符的长度（每个中文算2个字符）.
     *
     * @param str 指定的字符串
     * @return 中文字符的长度
     */
    public static int chineseLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        if (!isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                /* 获取一个字符 */
                String temp = str.substring(i, i + 1);
                /* 判断是否为中文字符 */
                if (temp.matches(chinese)) {
                    valueLength += 2;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取字符串的长度.
     *
     * @param str 指定的字符串
     * @return 字符串的长度（中文字符计2个）
     */
    public static int strLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    // 中文字符长度为2
                    valueLength += 2;
                } else {
                    // 其他字符长度为1
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取指定长度的字符所在位置.
     *
     * @param str  指定的字符串
     * @param maxL 要取到的长度（字符长度，中文字符计2个）
     * @return 字符的所在位置
     */
    public static int subStringLength(String str, int maxL) {
        int currentIndex = 0;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < str.length(); i++) {
            // 获取一个字符
            String temp = str.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength += 1;
            }
            if (valueLength >= maxL) {
                currentIndex = i;
                break;
            }
        }
        return currentIndex;
    }

    /**
     * 描述：是否只是字母和数字.
     *
     * @param str 指定的字符串
     * @return 是否只是字母和数字:是为true，否则false
     */
    public static Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String expr = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * 描述：是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：从输入流中获得String.
     *
     * @param is 输入流
     * @return 获得的String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // 最后一个\n删除
            if (sb.indexOf("\n") != -1
                    && sb.lastIndexOf("\n") == sb.length() - 1) {
                sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    the str
     * @param length 指定字节长度
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length) {
        return cutString(str, length, "");
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    文本
     * @param length 字节长度
     * @param dot    省略符号
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length, String dot) {
        int strBLen = strlen(str, "GBK");
        if (strBLen <= length) {
            return str;
        }
        int temp = 0;
        StringBuffer sb = new StringBuffer(length);
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(c);
            if (c > 256) {
                temp += 2;
            } else {
                temp += 1;
            }
            if (temp >= length) {
                if (dot != null) {
                    sb.append(dot);
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串从第一个指定字符.
     *
     * @param str1   原文本
     * @param str2   指定字符
     * @param offset 偏移的索引
     * @return 截取后的字符串
     */
    public static String cutStringFromChar(String str1, String str2, int offset) {
        if (isEmpty(str1)) {
            return "";
        }
        int start = str1.indexOf(str2);
        if (start != -1) {
            if (str1.length() > start + offset) {
                return str1.substring(start + offset);
            }
        }
        return "";
    }

    /**
     * 描述：获取字节长度.
     *
     * @param str     文本
     * @param charset 字符集（GBK）
     * @return the int
     */
    public static int strlen(String str, String charset) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = str.getBytes(charset).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 获取大小的描述.
     *
     * @param size 字节个数
     * @return 大小的描述
     */
    public static String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                // size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    // size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    /**
     * 描述：ip地址转换为10进制数.
     *
     * @param ip the ip
     * @return the long
     */
    public static long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16
                | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    /**
     * 获取UUID
     *
     * @return 32UUID小写字符串
     */
    public static String gainUUID() {
        String strUUID = UUID.randomUUID().toString();
        strUUID = strUUID.replaceAll("-", "").toLowerCase();
        return strUUID;
    }


    /**
     * 手机号码，中间4位星号替换
     *
     * @param phone 手机号
     * @return 星号替换的手机号
     */
    public static String phoneNoHide(String phone) {
        // 括号表示组，被替换的部分$n表示第n组的内容
        // 正则表达式中，替换字符串，括号的意思是分组，在replace()方法中，
        // 参数二中可以使用$n(n为数字)来依次引用模式串中用括号定义的字串。
        // "(\d{3})\d{4}(\d{4})", "$1****$2"的这个意思就是用括号，
        // 分为(前3个数字)中间4个数字(最后4个数字)替换为(第一组数值，保持不变$1)(中间为*)(第二组数值，保持不变$2)
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 银行卡号，保留最后4位，其他星号替换
     *
     * @param cardId 卡号
     * @return 星号替换的银行卡号
     */
    public static String cardIdHide(String cardId) {
        return cardId.replaceAll("\\d{15}(\\d{3})", "**** **** **** **** $1");
    }

    /**
     * 身份证号，中间10位星号替换
     *
     * @param id 身份证号
     * @return 星号替换的身份证号
     */
    public static String idHide(String id) {
        return id.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1** **** ****$2");
    }

    /**
     * 是否为车牌号（沪A88888）
     *
     * @param vehicleNo 车牌号
     * @return 是否为车牌号
     */

    public static boolean checkVehicleNo(String vehicleNo) {
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$");
        return pattern.matcher(vehicleNo).find();

    }


    /**
     * 判断字符串是否为连续数字 45678901等
     *
     * @param str 待验证的字符串
     * @return 是否为连续数字
     */
    public static boolean isContinuousNum(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!isNumber(str)) {
            return true;
        }
        int len = str.length();
        for (int i = 0; i < len - 1; i++) {
            char curChar = str.charAt(i);
            char verifyChar = (char) (curChar + 1);
            if (curChar == '9') {
                verifyChar = '0';
            }
            char nextChar = str.charAt(i + 1);
            if (nextChar != verifyChar) {
                return false;
            }
        }
        return true;
    }


    /**
     * 是否是纯字母
     *
     * @param str 待验证的字符串
     * @return 是否是纯字母
     */
    public static boolean isAlphaBetaString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        Pattern p = Pattern.compile("^[a-zA-Z]+$");// 从开头到结尾必须全部为字母或者数字
        Matcher m = p.matcher(str);

        return m.find();
    }

    /**
     * 判断字符串是否为连续字母 xyZaBcd等
     *
     * @param str 待验证的字符串
     * @return 是否为连续字母
     */
    public static boolean isContinuousWord(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!isAlphaBetaString(str)) {
            return true;
        }
        int len = str.length();
        String local = str.toLowerCase();
        for (int i = 0; i < len - 1; i++) {
            char curChar = local.charAt(i);
            char verifyChar = (char) (curChar + 1);
            if (curChar == 'z') {
                verifyChar = 'a';
            }
            char nextChar = local.charAt(i + 1);
            if (nextChar != verifyChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是日期
     * 20120506 共八位，前四位-年，中间两位-月，最后两位-日
     *
     * @param date    待验证的字符串
     * @param yearlen yearlength
     * @return 是否是真实的日期
     */
    public static boolean isRealDate(String date, int yearlen) {
        int len = 4 + yearlen;
        if (date == null || date.length() != len) {
            return false;
        }

        if (!date.matches("[0-9]+")) {
            return false;
        }

        int year = Integer.parseInt(date.substring(0, yearlen));
        int month = Integer.parseInt(date.substring(yearlen, yearlen + 2));
        int day = Integer.parseInt(date.substring(yearlen + 2, yearlen + 4));

        if (year <= 0) {
            return false;
        }
        if (month <= 0 || month > 12) {

            return false;
        }
        if (day <= 0 || day > 31) {

            return false;
        }

        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return day > 30 ? false : true;
            case 2:
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {

                    return day > 29 ? false : true;
                }
                return day > 28 ? false : true;
            default:
                return true;
        }
    }


    /**
     * 判断网络是否连接
     *
     * @param context 上下文
     * @return boolean 网络连接状态
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            //获取连接对象
            if (mNetworkInfo != null) {
                //判断是TYPE_MOBILE网络
                if (ConnectivityManager.TYPE_MOBILE == mNetworkInfo.getType()) {
                    Log.i("isNetworkConnected", "网络连接类型为：TYPE_MOBILE");
                    //判断移动网络连接状态
                    NetworkInfo.State STATE_MOBILE = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                    if (STATE_MOBILE == NetworkInfo.State.CONNECTED) {
                        Log.i("isNetworkConnected", "网络连接类型为：TYPE_MOBILE, 网络连接状态CONNECTED成功！");
                        return mNetworkInfo.isAvailable();
                    }
                }
                //判断是TYPE_WIFI网络
                if (ConnectivityManager.TYPE_WIFI == mNetworkInfo.getType()) {
                    Log.i("isNetworkConnected", "网络连接类型为：TYPE_WIFI");
                    //判断WIFI网络状态
                    NetworkInfo.State STATE_WIFI = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                    if (STATE_WIFI == NetworkInfo.State.CONNECTED) {
                        Log.i("isNetworkConnected", "网络连接类型为：TYPE_WIFI, 网络连接状态CONNECTED成功！");
                        return mNetworkInfo.isAvailable();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REGEX_HTML = "<[^>]+>";


    /**
     * 去掉字符串中的html标签
     *
     * @param str
     * @return
     */
    public static String delHtmlTag(String str) {
        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(str);
        str = m_html.replaceAll("");
        return str;
    }


}
