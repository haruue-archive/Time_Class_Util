package com.ant.jobgod.jobgod.util;

/**
 * Level4 将自己的类实现这个接口，完成所有功能。
 */
public interface ITime {
    void set(long timeStamp);
    void set(int year, int month, int day, int hour, int minute, int second);

    void setYear(int year);
    int getYear();
    void setMonth(int month);
    int getMonth();
    void setDay(int day);
    int getDay();
    void setHour(int hour);
    int getHour();
    void setMinute(int minute);
    int getMinute();
    void setSecond(int second);
    int getSecond();


    /**
     * 取星期几
     * @return
     */
    int getWeek();

    /**
     * 取当周是当年第几周
     * @return
     */
    int getWeekOfYear();

    /**
     * 取当天是当年第几天
     * @return
     */
    int getDayOfYear();

    /**
     *  取时间戳
     * @return
     */
    long getTimeStamp();

    /**
     * 时间加减
     * @param data
     */
    void add(ITime data);
    void minus(ITime data);


    /**
     * 时间格式化解析。
     * 例：输入yyyy年MM月dd日 hh:mm:ss  与 2015年10月14日 08:00:00 来设置时间
     * @param time 时间文本
     * @param format 年:y		月:M		日:d		时:h(12制)/H(24值)	分:m		秒:s		毫秒:S
     */
    void parse(String time, String format);


    /**
     * 时间格式化。
     * 例：输入yyyy年MM月dd日 hh:mm:ss  则返回2015年10月14日 08:00:00
     * @param format 格式 年:y		月:M		日:d		时:h(12制)/H(24值)	分:m		秒:s		毫秒:S
     * @return 格式化时间文本
     */
    String format(String format);


    /**
     * 时区切换
     * @param timeZone 时区名字。eg:GMT,UTC  ,CCT ,JST等。对时间进行转换。默认CCT。
     */
    void setTimeZone(String timeZone);
}