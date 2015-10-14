// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 2 (20151011)          *
// * Level 4 - Make a Class for Time Convert     *
// * Author:  Haruue Icymoon                     *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

import com.ant.jobgod.jobgod.util.ITime;

public class Time implements ITime {
    private int year = 1970, month = 1, day = 1, hour = 0, minute = 0, second = 0;
    private long timeStamp;

    //necessary math function
    private static boolean isLeapYear(int year) {
        return ((((year % 100) == 0) && (year % 400 == 0)) || (((year % 100) != 0) && ((year % 4) == 0)));
    }

    private static int daysOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) return 29;
            else return 28;
        }
        return 0; //ERROR month
    }

    private static int daysOfYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    private static long secondsOfMonth(int year, int month) {
        return 86400 * (long) daysOfMonth(year, month);
    }

    public static long secondsOfYear(int year) {
        return isLeapYear(year) ? 31622400 : 31536000;
    }


    @Override
    public void set(long timeStamp) {

    }

    @Override
    public void set(int year, int month, int day, int hour, int minute, int second) {

    }

    @Override
    public void setYear(int year) {
        this.year=year;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setMonth(int month) {
        this.month=month;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public void setDay(int day) {
        this.day=day;
    }

    @Override
    public int getDay() {
        return day;
    }

    @Override
    public void setHour(int hour) {
        this.hour=hour;
    }

    @Override
    public int getHour() {
        return hour;
    }

    @Override
    public void setMinute(int minute) {
        this.minute=minute;
    }

    @Override
    public int getMinute() {
        return minute;
    }

    @Override
    public void setSecond(int second) {
        this.second=second;
    }

    @Override
    public int getSecond() {
        return second;
    }

    /**
     * 取星期几
     *
     * @return
     */
    @Override
    public int getWeek() {
        return 0;
    }

    /**
     * 取当周是当年第几周
     *
     * @return
     */
    @Override
    public int getWeekOfYear() {
        return 0;
    }

    /**
     * 取当天是当年第几天
     *
     * @return
     */
    @Override
    public int getDayOfYear() {
        return 0;
    }

    /**
     * 取时间戳
     *
     * @return
     */
    @Override
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * 时间加减
     *
     * @param data
     */
    @Override
    public void add(ITime data) {

    }

    @Override
    public void minus(ITime data) {

    }

    /**
     * 时间格式化解析。
     * 例：输入yyyy年MM月dd日 hh:mm:ss  与 2015年10月14日 08:00:00 来设置时间
     *
     * @param time   时间文本
     * @param format 年:y		月:M		日:d		时:h(12制)/H(24值)	分:m		秒:s		毫秒:S
     */
    @Override
    public void parse(String time, String format) {

    }

    /**
     * 时间格式化。
     * 例：输入yyyy年MM月dd日 hh:mm:ss  则返回2015年10月14日 08:00:00
     *
     * @param format 格式 年:y		月:M		日:d		时:h(12制)/H(24值)	分:m		秒:s		毫秒:S
     * @return 格式化时间文本
     */
    @Override
    public String format(String format) {
        return null;
    }

    /**
     * 时区切换
     *
     * @param timeZone 时区名字。eg:GMT,UTC  ,CCT ,JST等。对时间进行转换。默认CCT。
     */
    @Override
    public void setTimeZone(String timeZone) {

    }
}
