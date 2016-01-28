// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 2 (20151011)          *
// * Level 4 - Make a Class for Time Convert     *
// * Author:  Haruue Icymoon                     *
// * Time:    Thu Oct 15 21:23:57 CST 2015       *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

package cn.com.caoyue.util.time;

/**
 * 时间处理工具 <br>
 * 这个类用作时间处理，支持时间戳转换、时区转换、格式化时间解析与输出和时间加减功能 <br>
 * 这个类直接定义出来的是一个时间点，要定义时间差，请使用 DeltaTime 子类<br>
 * 注意：如果没有定义，默认的时区将为 CCT(GMT +8:00) <br>
 * 注意：毫秒将被另外计算而不会算入时间戳中，对毫秒的处理很不完善而且容易丢失，不建议使用毫秒<br>
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 * @version 1.2
 */
public class Time {
    private int year = 1970, month = 1, day = 1, hour = 0, minute = 0, second = 0, timeZone = 8, microSecond = 0;
    private long timeStamp;

    /**
     * 构造一个空的时间处理工具<br>
     * 默认定义的时间是 1970 年 1 月 1 日 0:00:00.000 CCT(GMT +8:00)
     */
    public Time() {

    }

    /**
     * 复制一个时间处理工具<br>
     * 此函数的原理是将原时间的时间戳与时区进行复制，然后重新计算其他数值
     * @param origTime 被复制的时间
     */
    public Time(Time origTime) {
        timeStamp = origTime.getTimeStamp();
        timeZone = origTime.getTimeZone();
        stampToHuman();
    }

    /**
     * 用已知的时间戳构造一个时间处理工具<br>
     * 注意：默认的时区为 CCT(GMT +8:00)
     * @param timeStamp 时间戳
     */
    public Time(long timeStamp) {
        set(timeStamp);
    }

    /**
     * 用已知的时间戳和给定的时区构造一个时间处理工具<br>
     * @param timeStamp 时间戳
     * @param timeZone 时区
     */
    public Time(long timeStamp, int timeZone) {
        this.timeZone = timeZone;
        set(timeStamp);
    }

    /**
     * 用给定的时间构造时间处理工具<br>
     * 注意：默认时区为 CCT(GMT +8:00)
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 时
     * @param minute 分
     * @param second 秒
     */
    public Time(int year, int month, int day, int hour, int minute, int second) {
        set(year, month, day, hour, minute, second);
    }

    /**
     * 用给定的时间和时区构造时间处理工具
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @param timeZone 时区
     */
    public Time(int year, int month, int day, int hour, int minute, int second, int timeZone) {
        this.timeZone = timeZone;
        set(year, month, day, hour, minute, second);
    }

    /**
     * 格式化解析时间字符串。并构造时间处理工具<br>
     * 例：输入yyyy年MM月dd日 hh:mm:ss  与 2015年10月14日 08:00:00 来设置时间<br>
     * 注意：默认的时区是 CCT(GMT +8:00)
     * @param time   时间字符串
     * @param format 年:y		月:M		日:d		时:h(12制)/H(24值)	分:m		秒:s		毫秒:S
     */
    public Time(String time, String format) {
        parse(time, format);
    }

    /**
     * 判断一年是否为闰年
     * @param year 需要判断的年份
     * @return 闰年则为 true
     */
    public static boolean isLeapYear(int year) {
        return ((((year % 100) == 0) && (year % 400 == 0)) || (((year % 100) != 0) && ((year % 4) == 0)));
    }

    /**
     * 获取某年中的某个月的天数，如果对应月份不存在则抛出 IllegalArgumentException 异常<br>
     * @param year 年
     * @param month 月
     * @return 该月的天数
     * @throws IllegalArgumentException 找不到月份
     */
    public static int daysOfMonth(int year, int month) {
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
        throw new IllegalArgumentException();
    }

    /**
     * 获取某年的总天数
     * @param year 年
     * @return 这一年的天数
     */
    public static int daysOfYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    /**
     * 获取某年中的某月的总秒数，对应月份不存在则抛出 IllegalArgumentException 异常
     * @param year 年
     * @param month 月
     * @return 该月的秒数
     * @throws IllegalArgumentException 找不到月份
     */
    public static long secondsOfMonth(int year, int month) {
        return 86400 * (long) daysOfMonth(year, month);
    }

    /**
     * 获取某年的总秒数
     * @param year 年
     * @return 这一年的总秒数
     */
    public static long secondsOfYear(int year) {
        return isLeapYear(year) ? 31622400 : 31536000;
    }

    //stamp --> human
    private void stampToHuman() {
        //init timeHuman
        year = 1970;
        month = 1;
        day = 1;
        hour = 0;
        minute = 0;
        second = 0;
        //add timezone
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
        //init stamp
        long stamp = 0;
        //sum the seconds before the input year
        for (int year = 1970; year < this.year; year++) {
            stamp += secondsOfYear(year);
        }
        //sum the seconds in the input year and before the input month
        for (int month = 1; month < this.month; month++) {
            stamp += secondsOfMonth(this.year, month);
        }
        //sum the seconds in the input month and before the input day, day = 0 will be use in add() and minus()
        stamp += (day != 0) ? (86400 * (this.day - 1)) : 0;
        //sum the seconds in the input day
        stamp += 3600 * (this.hour);
        stamp += 60 * (this.minute);
        stamp += this.second;
        stamp -= (long) (this.timeZone * 3600);
        timeStamp = stamp;
    }

    //human --> stamp, year < 1970
    private void negativeHumanToStamp() {
        //init stamp
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

    /**
     * 比较两个时间是否相等<br>
     * 仅仅比较两个时间的时间戳
     * @param subTime 被比较的时间
     * @return 相等则为 true
     */
    public boolean equals(Time subTime) {
        return timeStamp == subTime.getTimeStamp();
    }

    /**
     * 转换为字符串<br>
     * 相当于 format("yyyy-MM-dd HH:mm:ss")
     * @return 转换成的字符串
     */
    @Override
    public String toString() {
        return format("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 转换为字符串<br>
     * 相当于 format(String format)
     *
     * @param format 格式字符串，参考 format(String format)
     * @return 格式化好的字符串
     */
    public String toString(String format) {
        return format(format);
    }

    /**
     * 以给定时间戳设置时间
     * @param timeStamp 时间戳
     */
    public void set(long timeStamp) {
        setTimeStamp(timeStamp);
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        stampToHuman();
    }

    /**
     * 以给定的时间设置时间
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     */
    public void set(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        humanToStamp();
    }

    public void setYear(int year) {
        this.year = year;
        humanToStamp();
    }

    public int getYear() {
        return year;
    }

    public void setMonth(int month) {
        this.month = month;
        humanToStamp();
    }

    public int getMonth() {
        return month;
    }

    public void setDay(int day) {
        this.day = day;
        humanToStamp();
    }

    public int getDay() {
        return day;
    }

    public void setHour(int hour) {
        this.hour = hour;
        humanToStamp();
    }

    public int getHour() {
        return hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        humanToStamp();
    }

    public int getMinute() {
        return minute;
    }

    public void setSecond(int second) {
        this.second = second;
        humanToStamp();
    }

    public int getSecond() {
        return second;
    }

    /**
     * 取星期几
     *
     * @return 星期几
     */
    public int getWeek() {
        int year = this.year;
        int month = this.month;
        if (month < 3) {
            month += 12;
            --year;
        }
        return (day + 1 + 2 * month + 3 * (month + 1) / 5 + year + (year >> 2) - year / 100 + year / 400) % 7;
    }

    /**
     * 取当周是当年第几周<br>
     * 注意：每年的第一个星期一所在的星期被认为是这一年的第一周
     * @return 第几周
     */
    public int getWeekOfYear() {
        int firstMonday, year = this.year;
        for (firstMonday = 1; ; firstMonday++) {
            Time tmpTime = new Time(year, 1, firstMonday, 0, 0, 0);
            if (tmpTime.getWeek() == 1)
                break;
        }
        if (getDayOfYear() < firstMonday) {
            if (getWeek() == 0) {
                return 1;
            }
            int firstMondayOfLastYear;
            year--;
            for (firstMondayOfLastYear = 1; ; firstMondayOfLastYear++) {
                Time tmpTime = new Time(year, 1, firstMondayOfLastYear, 0, 0, 0);
                if (tmpTime.getWeek() == 1)
                    break;
            }
            return (daysOfYear(year) - firstMondayOfLastYear + getDayOfYear()) / 7 + 1;
        }
        return (getDayOfYear() - firstMonday + 1) / 7 + 1;
    }

    /**
     * 取当天是当年第几天
     *
     * @return 当天是当年的天数
     */
    public int getDayOfYear() {
        int result = 0;
        for (int month = 1; month < this.month; month++) {
            result += daysOfMonth(this.year, month);
        }
        result += this.day;
        return result;
    }

    /**
     * 取时间戳
     *
     * @return 时间戳
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * 时间差值<br>
     *      这个类表示一个时间差值，它表示一段时间，用于时间加减时的参数<br>
     *      由于每个月的天数不一样，所以此类没有 month 和 year 属性<br>
     *      你可以传入一个非法值，比如 0天29小时288分钟300秒 ，这个类会自动纠正成 1天9小时33分钟0秒<br>
     *      时间差值的正负由时间戳的正负或者天数的正负决定
     */
    public static class DeltaTime {
        int day = 0, hour = 0, minute = 0, second = 0;
        long timeStamp = 0;

        /**
         * 使用给定的时间戳构造一个时间差
         *
         * @param timeStamp 给定的时间戳
         */
        public DeltaTime(long timeStamp) {
            this.timeStamp = timeStamp;
            stampToHuman();
        }

        /**
         * 使用给定的时间创建一个时间差值
         *
         * @param day    天数
         * @param hour   小时
         * @param minute 分钟
         * @param second 秒
         */
        public DeltaTime(int day, int hour, int minute, int second) {
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            humanToStamp();
            stampToHuman();
        }

        private void stampToHuman() {
            day = 0;
            hour = 0;
            minute = 0;
            second = 0;
            boolean isNegative = false;
            long stamp = timeStamp;
            if (stamp < 0) {
                isNegative = true;
                stamp = -stamp;
            }
            //get day
            day = (isNegative ? (-1) : 1) * (int) ((stamp) / 86400);
            stamp %= 86400;
            //get hour
            hour = (int) ((stamp) / 3600);
            stamp %= 3600;
            //get minute
            minute = (int) ((stamp) / 60);
            stamp %= 60;
            //get second
            second = (int) stamp;
        }

        private void humanToStamp() {
            timeStamp = ((day >= 0) ? 1 : (-1)) * ((((day >= 0) ? 1 : (-1)) * day) * 86400 + hour * 3600 + minute * 60 + second);
        }

        public int getDay() {
            return day;
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }

        public int getSecond() {
            return second;
        }

        public long getTimeStamp() {
            return timeStamp;
        }
    }

    /**
     * 时间加<br>
     * 将当前时间与一个时间差相加，返回结果
     *
     * @param data 时间差
     * @return 相加的结果 this + data
     */
    public Time add(DeltaTime data) {
        return new Time(data.timeStamp + timeStamp);
    }

    /**
     * 时间减<br>
     * 将当前时间与另一个时间相减，返回结果
     *
     * @param data 需要相减的另一个时间
     * @return 相减的结果 this - data
     */
    public DeltaTime minus(Time data) {
        return new DeltaTime(this.timeStamp - data.timeStamp);
    }

    /**
     * 时间减<br>
     * 将当前时间与一个时间差相减，返回结果
     *
     * @param data 时间差
     * @return 相减的结果 this - data
     */
    public Time minus(DeltaTime data) {
        return new Time(timeStamp - data.timeStamp);
    }

    /**
     * 给 int 属性加一个数值
     *
     * @param field 属性，必须是 cn.com.caoyue.util.time.Time.Field 中的 int 值
     * @param value 增加的数值
     * @return 结果 this + value
     * @throws IllegalArgumentException 找不到属性
     * @see Time.Field
     */
    public Time add(int field, int value) {
        Time time = new Time(this.timeStamp, this.timeZone);
        time.set(field, this.get(field) + value);
        return time;
    }

    /**
     * 给 long 属性加一个数值
     *
     * @param field 属性，必须是 cn.com.caoyue.util.time.Time.Field 中的 long 值
     * @param value 增加的数值
     * @return 结果 this + value
     * @throws IllegalArgumentException 找不到属性
     * @see Time.Field
     */
    public Time add(long field, long value) {
        Time time = new Time(this.timeStamp, this.timeZone);
        time.set(field, this.get(field) + value);
        return time;
    }

    /**
     * 给 int 属性减一个数值
     *
     * @param field 属性，必须是 cn.com.caoyue.util.time.Time.Field 中的 int 值
     * @param value 减少的数值
     * @return 结果 this - value
     * @throws IllegalArgumentException 找不到属性
     * @see Time.Field
     */
    public Time minus(int field, int value) {
        return add(field, -value);
    }

    /**
     * 给 long 属性减一个数值
     *
     * @param field 属性，必须是 cn.com.caoyue.util.time.Time.Field 中的 long 值
     * @param value 减少的数值
     * @return 结果 this - value
     * @throws IllegalArgumentException 找不到属性
     * @see Time.Field
     */
    public Time minus(long field, long value) {
        return add(field, -value);
    }

    /**
     * 时间格式化解析<br>
     * 例：输入yyyy年MM月dd日 hh:mm:ss  与 2015年10月14日 08:00:00 来设置时间 <br>
     * 注意：默认时间戳为 CCT(GMT +8:00)
     * @param time   时间字符串
     * @param format 年:y		月:M		日:d		时:h(12制)/H(24值)	分:m		秒:s		毫秒:S
     */
    public void parse(String time, String format) {
        String[] timeArray = time.split("[^0-9]");
        String thisCase, lastCase = "";
        int index = 0;
        for (; timeArray[index].equals(""); index++) ;
        for (int i = 0; i < format.length(); i++) {
            if (index >= timeArray.length) {
                break;
            }
            for (; timeArray[index].isEmpty(); index++) ;
            thisCase = format.substring(i, i + 1);
            if (thisCase.matches("[yMdHhmsS]") && !thisCase.equals(lastCase)) {
                switch (thisCase) {
                    case "y":
                        year = Integer.parseInt(timeArray[index]);
                        break;
                    case "M":
                        month = Integer.parseInt(timeArray[index]);
                        break;
                    case "d":
                        day = Integer.parseInt(timeArray[index]);
                        break;
                    case "H":
                    case "h":
                        hour = Integer.parseInt(timeArray[index]);
                        break;
                    case "m":
                        minute = Integer.parseInt(timeArray[index]);
                        break;
                    case "s":
                        second = Integer.parseInt(timeArray[index]);
                        break;
                    case "S":
                        microSecond = Integer.parseInt(timeArray[index]);
                        break;
                }
                index++;
            }
            lastCase = thisCase;
        }
        humanToStamp();
    }

    /**
     * 时间格式化为字符串<br>
     * 例：输入yyyy年MM月dd日 hh:mm:ss  则返回2015年10月14日 08:00:00
     *
     * @param format 格式<br>
     *               yyyy 年<br>
     *               y    年<br>
     *               MM   月（不足 10 时补 0）<br>
     *               M    月<br>
     *               dd   日（不足 10 时补 0）<br>
     *               d    日（不足 10 时补 0）<br>
     *               HH   时（24 小时制，不足 10 时补 0）<br>
     *               H    时（24 小时制，不足 10 时补 0）<br>
     *               hh   时（12 小时制，不足 10 时补 0）<br>
     *               h    时（12 小时制，不足 10 时补 0）<br>
     *               mm   分（不足 10 时补 0）<br>
     *               m    分<br>
     *               ss   秒（不足 10 时补 0）<br>
     *               s    秒<br>
     *               SS   毫秒（不足 10 时补 0）<br>
     *               S    毫秒
     * @return 格式化时间文本
     */
    public String format(String format) {
        format = format.replaceAll("yyyy", Integer.toString(year));
        format = format.replaceAll("y", Integer.toString(year));
        format = format.replaceAll("MM", (month < 10) ? "0" + Integer.toString(month) : Integer.toString(month));
        format = format.replaceAll("M", Integer.toString(month));
        format = format.replaceAll("dd", (day < 10) ? "0" + Integer.toString(day) : Integer.toString(day));
        format = format.replaceAll("d", Integer.toString(day));
        format = format.replaceAll("HH", (hour < 10) ? "0" + Integer.toString(hour) : Integer.toString(hour));
        format = format.replaceAll("H", Integer.toString(hour));
        format = format.replaceAll("hh", (hour == 0) ? "12" : ((hour % 12 < 10) ? "0" + Integer.toString(hour % 12) : Integer.toString(hour % 12)));
        format = format.replaceAll("h", (hour == 0) ? "12" : Integer.toString(hour % 12));
        format = format.replaceAll("mm", (minute < 10) ? "0" + Integer.toString(minute) : Integer.toString(minute));
        format = format.replaceAll("m", Integer.toString(minute));
        format = format.replaceAll("ss", (second < 10) ? "0" + Integer.toString(second) : Integer.toString(second));
        format = format.replaceAll("s", Integer.toString(second));
        format = format.replaceAll("SS", (microSecond < 10) ? "0" + Integer.toString(microSecond) : Integer.toString(microSecond));
        format = format.replaceAll("S", Integer.toString(microSecond));
        return format;
    }

    /**
     * 时区切换
     *
     * @param timeZone 时区名字。eg:GMT,UTC  ,CCT ,JST等。对时间进行转换。默认CCT。<br>
     * @deprecated 请使用 setTimeZone(int) 与 TimeZone 子类代替。
     * @see Time.TimeZone
     */
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

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
        stampToHuman();
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setMicroSecond(int microSecond) {
        this.microSecond = microSecond;
    }

    /**
     * 属性列表<br>
     * 用于 set, get, add, minus 函数的参数
     */
    public static class Field {
        public final static int year = 0;
        public final static int month = 1;
        public final static int day = 2;
        public final static int hour = 3;
        public final static int minute = 4;
        public final static int second = 5;
        public final static long timeStamp = 6;
        public final static int microSecond = 7;
        public final static int timeZone = 8;
    }

    /**
     * 获取 int 属性的值
     *
     * @param field 属性，必须是 cn.com.caoyue.util.time.Time.Field 中的 int 值
     * @return 该属性的值
     * @throws IllegalArgumentException 找不到属性
     * @see Time.Field
     */
    public int get(int field) {
        switch (field) {
            case Field.year:
                return getYear();
            case Field.month:
                return getMonth();
            case Field.day:
                return getDay();
            case Field.minute:
                return getMinute();
            case Field.second:
                return getSecond();
            case Field.microSecond:
                return microSecond;
            case Field.timeZone:
                return timeZone;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * 获取 long 属性的值
     *
     * @param field 属性，必须是 cn.com.caoyue.util.time.Time.Field 中的 long 值
     * @return 该属性的值
     * @throws IllegalArgumentException 找不到该属性
     * @see Time.Field
     */
    public long get(long field) {
        if (field == Field.timeStamp) {
            return getTimeStamp();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 设置 int 属性的值
     *
     * @param field 属性，必须是 cn.com.caoyue.util.time.Time.Field 中的 int 值
     * @param value 该属性的值
     * @throws IllegalArgumentException 找不到该属性
     * @see Time.Field
     */
    public void set(int field, int value) {
        switch (field) {
            case Field.year:
                setYear(value);
                break;
            case Field.month:
                setMonth(value);
                break;
            case Field.day:
                setDay(value);
                break;
            case Field.minute:
                setMinute(value);
                break;
            case Field.second:
                setSecond(value);
                break;
            case Field.microSecond:
                setMicroSecond(value);
                break;
            case Field.timeZone:
                setTimeZone(value);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * 设置 long 属性的值
     *
     * @param field 属性，必须是 cn.com.caoyue.util.time.Time.Field 中的 long 值
     * @param value 该属性的值
     * @throws IllegalArgumentException 找不到该属性
     * @see Time.Field
     */
    public void set(long field, long value) {
        if (field == Field.timeStamp) {
            setTimeStamp(value);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 国际时间缩写列表<br>
     * 不支持夏令时
     */
    public static class TimeZone {

        public final static int NZDT = 13;
        public final static int IDLE = 12;
        public final static int NZST = 12;
        public final static int NZT = 12;
        public final static int AESST = 11;
        public final static int EAST = 10;
        public final static int GST = 10;
        public final static int LIGT = 10;
        public final static int WDT = 9;
        public final static int AWSST = 9;
        public final static int JST = 9;
        public final static int KST = 9;
        public final static int WST = 8;
        public final static int AWST = 8;
        public final static int CCT = 8;
        public final static int BT = 3;
        public final static int EETDST = 3;
        public final static int CETDST = 2;
        public final static int EET = 2;
        public final static int FWT = 2;
        public final static int IST = 2;
        public final static int MEST = 2;
        public final static int METDST = 2;
        public final static int SST = 2;
        public final static int BST = 1;
        public final static int CET = 1;
        public final static int DNT = 1;
        public final static int FST = 1;
        public final static int MET = 1;
        public final static int MEWT = 1;
        public final static int MEZ = 1;
        public final static int NOR = 1;
        public final static int SET = 1;
        public final static int SWT = 1;
        public final static int WETDST = 1;
        public final static int GMT = 0;
        public final static int WET = 0;
        public final static int WAT = -1;
        public final static int ADT = -3;
        public final static int AST = -4;
        public final static int EDT = -4;
        public final static int CDT = -5;
        public final static int EST = -5;
        public final static int CST = -6;
        public final static int MDT = -6;
        public final static int MST = -7;
        public final static int PDT = -7;
        public final static int PST = -8;
        public final static int YDT = -8;
        public final static int HDT = -9;
        public final static int YST = -9;
        public final static int AHST = -10;
        public final static int CAT = -10;
        public final static int NT = -11;
        public final static int IDLW = -12;
    }

    /**
     * 星期列表<br>
     * getWeek() 的返回值
     */
    public static class Week {
        public final static int SUNDAY = 0;
        public final static int MONDAY = 1;
        public final static int TUESDAY = 2;
        public final static int WEDNESDAY = 3;
        public final static int THURSDAT = 4;
        public final static int FRIDAY = 5;
        public final static int SATURDAY = 6;
    }

}
