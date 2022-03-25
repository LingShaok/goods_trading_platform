package com.lingshao.util;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author string
 * @since on 2018/12/8.
 */
public class StringUtil {

    /**
     * 获取方法中指定注解的value值返回
     *
     * @param method               方法名
     * @param validationParamValue 注解的类名
     * @return
     */
    public static String getMethodAnnotationOne(Method method, String validationParamValue) {
        String retParam = null;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                String str = parameterAnnotations[i][j].toString();
                if (str.indexOf(validationParamValue) > 0) {
                    retParam = str.substring(str.indexOf("=") + 1, str.indexOf(")"));
                }
            }
        }
        return retParam;
    }

    /**
     * @param proto
     * @return
     * @throws Exception
     */
    public static String ISO2UTF8(String proto) throws Exception {

        return new String(proto.getBytes("ISO-8859-1"), "UTF-8");
    }

    public static boolean isValidURLAddress(String url) {
        String pattern = "([h]|[H])([t]|[T])([t]|[T])([p]|[P])([s]|[S]){0,1}://([^:/]+)(:([0-9]+))?(/\\S*)*";
        return url.matches(pattern);
    }

    /**
     * 将utf-8编码的汉字转为中文
     *
     * @param str
     * @return
     * @author zhaoqiang
     */
    public static String utf8Decoding(String str) {
        String result = str;
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkEmail(String email) {
        if (ComUtil.isEmpty(email)) {
            return false;
        }
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
     *
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 判断是否为数字(正负数都行)
     *
     * @param str 需要验证的字符串
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * @description 判断输入的字符串是男的还是女的，男0，女1，无法识别2
     * @author LingShao
     * @date 2021/4/9
     */
    public static Integer genderCheck(String inputStr) {
        if (inputStr.contains("男")) {
            return 0;
        } else if (inputStr.contains("女")) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * @description 获取list中相同元素的下标
     * @author LingShao
     * @date 2021/4/12
     */
    public static Map<String, List<Integer>> getSameFlag(Map<Integer, String> map) {
        Collection<String> values = map.values();
        List<String> list = Distinct(findRepeat(values));
        Map<String, List<Integer>> result = new HashMap<>();
        for (String value : list) {
            List<Integer> keyList = new ArrayList<>();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getValue().equals(value)) {
                    keyList.add(entry.getKey());
                }
            }
            result.put(value, keyList);
        }
        return result;
    }

    /**
     * @description 使用hashset方式去重
     * @author LingShao
     * @date 2021/4/13
     */
    public static <T> List<T> Distinct(Collection<T> datas) {
        if (datas == null) {
            return new ArrayList<>();
        }
        Set<T> set = new HashSet<>(datas);
        return new ArrayList<>(set);
    }

    /**
     * @description 找到重复的数据
     * @author LingShao
     * @date 2021/4/13
     */
    public static <T> List<T> findRepeat(Collection<T> datas) {
        if (datas instanceof Set) {
            return new ArrayList<>();
        }
        HashSet<T> set = new HashSet<T>();
        List<T> repeatEles = new ArrayList<T>();
        for (T t : datas) {
            if (set.contains(t)) {
                repeatEles.add(t);
            } else {
                set.add(t);
            }
        }
        return repeatEles;
    }

    /**
     * @description 获取汉字
     * @author LingShao
     * @date 2021/4/17
     */
    public static String getChinese(String str) {
        return str.replaceAll("[^\\u4e00-\\u9fa5]", "");
    }

    /**
     * @description 获取数字
     * @author LingShao
     * @date 2021/4/17
     */
    public static Integer getNumber(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return Integer.valueOf(m.replaceAll("").trim());
    }

    /**
     * @description 判断字符是否为正整数
     * @author LingShao
     * @date 2021/5/2
     */
    public static boolean isInteger(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
