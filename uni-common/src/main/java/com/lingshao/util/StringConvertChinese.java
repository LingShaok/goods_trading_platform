package com.lingshao.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LingShao
 * @date 2019/2/26 18:04.
 */
public class StringConvertChinese {

    public static String get(String input) {

        String units[] = {"","十","百","千","万","十","百","千","亿"};

        String nums[] = {"零","一","二","三","四","五","六","七","八","九","十"};

        List<String> result= new ArrayList<>();
        String out = "";
        Integer flag = 0;
        for(int i=0;i<input.length();i++) {
            String value = String.valueOf(input.charAt(i));
            if (!(value.equals("0") && flag == 0)){
                result.add(String.valueOf(input.charAt(i)));
                flag = 1;
            }
        }
        int back = 0;
        for(int i=0;i<result.size();i++) {
            if(!result.get(i).equals("0")) {
                back = result.size()-i-1;
                out += nums[Integer.parseInt(result.get(i))];
                out += units[back];
            }else {
                if(i==(result.size()-1)) {

                }else {
                    if(!result.get(i+1).equals("0")) {
                        out += nums[0];
                    }
                }
            }
        }
        Character first = out.charAt(0);
        Character second = null;
        if (out.length() > 1){
            second = out.charAt(1);
        }
        if (first.equals('一')){
            if (null != second && second == '十'){
                out = out.substring(1);
            }
        }
        return out;
    }
    public static String getChineseYear(Integer year) {
        String nums[] = {"〇","一","二","三","四","五","六","七","八","九"};
        String yearStr = year.toString();
        String out = "";
        for(int i = 0; i< yearStr.length(); i++) {
            out += nums[Integer.valueOf(String.valueOf(yearStr.charAt(i)))];
        }
        return out;
    }
}
