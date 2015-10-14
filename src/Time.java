// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 2 (20151011)          *
// * Level 4 - Make a Class for Time Convert     *
// * Author:  Haruue Icymoon                     *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

import com.ant.jobgod.jobgod.util.ITime;

public class Time implements ITime {
    private int year = 1970, month = 1, day = 1, hour = 0, minute = 0, second = 0, timeZone = 8;
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

    //stamp --> human
    private void stampToHuman() {
        //init timeHuman
        long timeStamp = this.timeStamp + (long) (3600 * timeZone);
        //when time stamp is negative
        if (timeStamp < 0) {
            negativeStampToHuman(timeStamp);
            return;
        }
        //get year
        while (timeStamp - secondsOfYear(year) >= 0) {
            timeStamp -= secondsOfYear(year);
            year++;
        }
        //get month
        while (timeStamp - secondsOfMonth(year, month) >= 0) {
            timeStamp -= secondsOfMonth(year, month);
            month++;
        }
        //get day
        day = 1 + (int) ((timeStamp) / 86400);
        timeStamp %= 86400;
        //get hour
        hour = (int) ((timeStamp) / 3600);
        timeStamp %= 3600;
        //get minute
        minute = (int) ((timeStamp) / 60);
        timeStamp %= 60;
        //get second
        second = (int) timeStamp;
    }

    //negative stamp --> human
    private void negativeStampToHuman(long timeStamp) {
        timeStamp = -timeStamp;
        //get time first
        second = (int) (((timeStamp % 60) == 0) ? 0 : (60 - (timeStamp % 60)));
        timeStamp -= timeStamp % 60;
        minute = (int) (((timeStamp % 3600) == 0) ? ((second != 0) ? 59 : 0) : (((second != 0) ? (59 - (timeStamp % 3600) / 60) : (60 - (timeStamp % 3600) / 60))));
        timeStamp -= timeStamp % 3600;
        hour = (int) (((timeStamp % 86400) == 0) ? ((minute != 0) ? 23 : 0) : (((minute != 0) ? (23 - (timeStamp % 86400) / 3600) : (24 - (timeStamp % 86400) / 3600))));
        timeStamp -= timeStamp % 86400;
        //get year and month
        year = 1969;
        month = 12;
        while (timeStamp >= secondsOfYear(year)) {
            timeStamp -= secondsOfYear(year);
            year--;
        }
        while (timeStamp >= secondsOfMonth(year, month)) {
            timeStamp -= secondsOfMonth(year, month);
            month--;
        }
        //get day
        day = daysOfMonth(year, month);
        day -= (int) (timeStamp / 86400);
    }

    //human --> stamp, year >= 1970
    private void humanToStamp() {
        if (year < 1970) {
            negativeHumanToStamp();
            return;
        }
        long stamp = 0;
        //sum the seconds before the input year
        for (int year = 1970; year < this.year; year++) {
            stamp += secondsOfYear(year);
        }
        //sum the seconds in the input year and before the input month
        for (int month = 1; month < this.month; month++) {
            stamp += secondsOfMonth(this.year, month);
        }
        //sum the seconds in the input month and before the input day
        stamp += 86400 * (this.day - 1);
        //sum the seconds in the input day
        stamp += 3600 * (this.hour);
        stamp += 60 * (this.minute);
        stamp += this.second;
        stamp -= (long) (this.timeZone * 3600);
        timeStamp = stamp;
    }

    //human --> stamp, year < 1970
    private void negativeHumanToStamp() {
        long stamp = 0;
        for (int year = 1969; year > this.year; year--) {
            stamp += secondsOfYear(year);
        }
        for (int month = 12; month > this.month; month--) {
            stamp += secondsOfMonth(this.year, month);
        }
        stamp += 86400 * (daysOfMonth(this.year, this.month) - this.day);
        stamp += (3600 * (23 - hour));
        stamp += (60 * (59 - minute));
        stamp += (60 - second);
        stamp += (long) (this.timeZone * 3600);
        timeStamp = -stamp;
    }

    @Override
    public void set(long timeStamp) {
        this.timeStamp = timeStamp;
        stampToHuman();
    }

    @Override
    public void set(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        humanToStamp();
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public int getDay() {
        return day;
    }

    @Override
    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public int getHour() {
        return hour;
    }

    @Override
    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public int getMinute() {
        return minute;
    }

    @Override
    public void setSecond(int second) {
        this.second = second;
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
        if (month < 3) {
            month += 12;
            --year;
        }
        return (day + 1 + 2 * month + 3 * (month + 1) / 5 + year + (year >> 2) - year / 100 + year / 400) % 7;
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
        switch (timeZone) {
            case "GMT":
            case "UTC":
                this.timeZone = 0;
                break;
            case "CCT":
                this.timeZone = 8;
                break;
            case "JST":
                this.timeZone = 9;
                break;
        }
        stampToHuman();
    }
}
