package com.lingshao.util;

import com.lingshao.constant.CommonConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @description 时间处理工具类
 * @author LingShao
 * @date 2021/4/18
 */
public class DateUtil {

    public static final String FORMAT_YEAR = "YYYY";


    public static Date stringTodate(String src) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(src);
        } catch (ParseException e) {
            DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = null;
            try {
                date2 = format2.parse(src);
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            return date2;
        }
        return date;
    }

    public static String dateToString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);
    }

    public static String dateToString3(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);
    }

    public static String stringToSimpleString(String src) {
        DateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat formatTwo = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatOne.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatTwo.format(date);
    }

    public static String stringToSimpleStringB(String src) {
        DateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat formatTwo = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        try {
            date = formatOne.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatTwo.format(date);
    }

    public static Date stringToDay(String src) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getDate(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);
    }

    public static String getDate2(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        return format.format(date);
    }

    public static Date stringToMonth(String src) {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getMonth(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    public static String getMonth(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);

        return format.format(date);
    }

    public static Date stringToYear(String src) {
        DateFormat format = new SimpleDateFormat("yyyy");
        Date date = null;
        try {
            date = format.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getTime(Date date) {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");

        return format.format(date);
    }

    /**
     * 格式化为2016.11.11
     *
     * @param date
     * @return
     */
    public static String dateToString1(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        return format.format(date);
    }

    /**
     * 获取每周的时间范围
     *
     * @param year
     * @param week
     * @return
     */
    public static String getRangeOfWeek(int year, int week) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week + 1);// 需要加1
        // 设置每周从周一开始
        cal.setFirstDayOfWeek(2);

        cal.set(Calendar.DAY_OF_WEEK, 2);
        String start = dateToString1(cal.getTime());

        cal.set(Calendar.DAY_OF_WEEK, 8);
        String end = dateToString1(cal.getTime());

        return start + "~" + end;
    }

    /**
     * 获取最近10年的年份信息
     *
     * @return
     */
    public static List<Integer> genYears() {
        List<Integer> years = new ArrayList<Integer>();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            years.add(year - i);
        }
        return years;
    }

    /**
     * 获得学校当前时间最近的入学年份 7月份之前为上一个（不包括） 9月份以后（包括）
     *
     * @return
     */
    public static Integer getCurrentyear() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;// 获取当前月份
        int year = cal.get(Calendar.YEAR);// 获取当前年份
        if (month >= 9) {
            year++;
        }
        if (month < 7) {
            year--;
        }
        return year;
    }

    /**
     * 判断是否属于当前学期(Grade表)
     *
     * @param startdate 学期开始时间
     * @param enddate   学期结束时间
     * @return false：不属于当前学期 true：属于当前学期
     */
    public static Boolean getIsCurrentTerm(String startdate, String enddate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date();
        Boolean flag = false;
        Date sd = null;
        Date ed = null;
        try {
            sd = sdf.parse(startdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            ed = sdf.parse(enddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (sd != null && ed != null) {
            if (sd.before(current) && ed.after(current)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 将时间转换成yyyy/MM/dd格式
     *
     * @param str
     * @return
     */
    public static String dateFormat(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d = stringTodate(str);
        String time = sdf.format(d);
        return time;
    }

    public static String dateFormatSecond(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = stringTodate(str);
        String time = sdf.format(d);
        return time;
    }

    /**
     * 将时间转换成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String dateFormat2(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = stringTodate(str);
        String time = sdf.format(d);
        return time;
    }

    public static String dateFormatNew(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date d = stringTodate(str);
        String time = sdf.format(d);
        return time;
    }

    public static String dateFormatMonth(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("m");
        Date d = stringTodate(str);
        String time = sdf.format(d);
        return time;
    }

    /**
     * 将时间转换成yyyy.MM.dd格式
     *
     * @param str
     * @return
     */
    public static String dateFormat3(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date d = stringTodate(str);
        String time = sdf.format(d);
        return time;
    }

    /**
     * 将时间转换成yyyy-MM-dd HH:mm:ss格式
     *
     * @param str
     * @return
     */
    public static String dateFormat4(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = stringTodate(str);
        String time = sdf.format(d);
        return time;
    }

    /**
     * 将yyyy/MM/dd格式转换成yyyy-MM-dd HH:mm:ss格式
     *
     * @param str
     * @return
     */
    public static String dateFormat5(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        str = str.replaceAll("/", "-") + " 00:00:00";
        return str;
    }

    public static String dateFormat6(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = stringToDay(str);
        return format.format(date);
    }

    public static String dateFormat7(String str) {
        if (ComUtil.isEmpty(str)) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("MM月dd日");
        Date date = stringToDay(str);
        return format.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowTime2() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(d);
    }

    /**
     * 获取当前系统前一天日期
     */
    public static Date getBeforDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * @param curdate
     * @return 返回时间串 20171011
     */
    public static String dateToString2(Date curdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(curdate);
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取上一年
     *
     * @return
     */
    public static int getLastYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) - 1;
    }

    /**
     * 获取下一年
     *
     * @return
     */
    public static int getFutureYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + 1;
    }

    public static String getDateByFormat(Date date, String formatYear) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatYear);
        return simpleDateFormat.format(date);
    }

    public static Integer getNowYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author patton   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param nowTime 当前时间
     * @param endTime 结束时间
     * @return
     * @author patton   判断当前时间是否大于结束时间
     */
    public static boolean greaterThanEndTime(Date nowTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param inputDate 输入时间
     * @return String
     * @description 截取输入时间的年份
     * @author LingShao
     * @date 2021/3/31
     */
    public static String getYear(Date inputDate) {
        DateFormat df2 = new SimpleDateFormat("yyyy");
        String year = df2.format(inputDate);
        return year;
    }

    /**
     * @param @param null:
     * @return
     * @description 获取起始日期间隔周数
     * @author LingShao
     * @date 2021/4/9
     */
    public static int getBetweenWeeks(String startDay, String endDay) {
        if (startDay.equals(endDay)) {
           return 0;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(startDay, formatter);
        String noWeek = date.getDayOfWeek().toString();
        for (int i = 0; i < CommonConstant.WEEK.WEEKARRAY.length; i++) {
            if (noWeek.equals(CommonConstant.WEEK.WEEKARRAY[i])) {
                noWeek = String.valueOf(i);
            }
        }
//        获取开始时间与这周周七的间隔
        int countStartWeekDays = CommonConstant.WEEKDAY_NUMBER.SEVEN - Integer.valueOf(noWeek);

//        计算开始到结束日期的天数
        int betweenDays = CommonConstant.WEEKDAY_NUMBER.ONE + (int) ChronoUnit.DAYS.between(LocalDate.parse(startDay), LocalDate.parse(endDay));
        if (betweenDays % CommonConstant.WEEKDAY_NUMBER.SEVEN == CommonConstant.WEEKDAY_NUMBER.ZERO) {
            betweenDays -= CommonConstant.WEEKDAY_NUMBER.ONE;
        }
        if (countStartWeekDays >= betweenDays % CommonConstant.WEEKDAY_NUMBER.SEVEN) {
            return betweenDays / CommonConstant.WEEKDAY_NUMBER.SEVEN + CommonConstant.WEEKDAY_NUMBER.ONE;
        } else {
            return betweenDays / CommonConstant.WEEKDAY_NUMBER.SEVEN + CommonConstant.WEEKDAY_NUMBER.TWO;
        }
    }

}