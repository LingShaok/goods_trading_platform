package com.lingshao.util;


import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @author LingShao
 * @since 2018-12-02
 */
public class ComUtil {

    public static boolean isEmpty(Object aObj) {
        //字符串
        if (aObj instanceof String) {
            return isEmpty((String) aObj);
        } else if (aObj instanceof Long) {
            //Long
            return isEmpty((Long) aObj);
        } else if (aObj instanceof Date) {
            //日期
            return isEmpty((Date) aObj);
        } else if (aObj instanceof Collection) {
            //集合
            return isEmpty((Collection) aObj);
        } else if (aObj instanceof Map) {
            //map
            return isEmpty((Map) aObj);
        } else if (aObj != null && aObj.getClass().isArray()) {
            //数组
            return isEmptyArray(aObj);
        } else {
            return isNull(aObj);
        }
    }

    private static boolean isEmptyArray(Object array) {
        int length = 0;
        if (array instanceof int[]) {
            length = ((int[]) array).length;
        } else if (array instanceof byte[]) {
            length = ((byte[]) array).length;
        } else if (array instanceof short[]) {
            length = ((short[]) array).length;
        } else if (array instanceof char[]) {
            length = ((char[]) array).length;
        } else if (array instanceof float[]) {
            length = ((float[]) array).length;
        } else if (array instanceof double[]) {
            length = ((double[]) array).length;
        } else if (array instanceof long[]) {
            length = ((long[]) array).length;
        } else if (array instanceof boolean[]) {
            length = ((boolean[]) array).length;
        } else {
            length = ((Object[]) array).length;
        }
        if (length == 0) {
            return true;
        }
        return false;
    }


    public static boolean isEmpty(Date aDate) {
        if (aDate == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Long aLong) {
        if (aLong == null) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isEmpty(Map m) {
        if (m == null || m.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Collection c) {
        if (c == null || c.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String aStr) {
        if (aStr == null || aStr.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    public static String trim(String aStr) {
        if (aStr == null) {
            return "";
        } else {
            return aStr.trim();
        }
    }

    public static boolean isNull(Object oStr) {
        if (oStr == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validMsg(String message) {
        String mess = ComUtil.trim(message);
        if (!ComUtil.isEmpty(mess) && !"EMPTY".equals(mess)) {
            return true;
        }
        return false;
    }

    public static boolean equals(String str1, String str2) {
        return str1 != null ? str1.equals(str2) : str2 == null;
    }

    public static boolean equals(Long L1, Long L2) {
        return L1 != null ? L1.equals(L2) : L2 == null;
    }

    public static boolean equals(Object obj1, Object obj2) {
        boolean result;
        if (obj1 != null) {
            result = obj1.equals(obj2);
        } else {
            result = (obj2 == null);
        }
        return result;
    }

    /**
     * 只能输入数字和字母
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }
    /**
     * 生成六位数的随机验证码
     *
     * @param charCount
     * @return
     */
    public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
