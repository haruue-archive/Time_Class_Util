// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 2 (20151011)          *
// * Level 4 - Make a Class for Time Convert     *
// * Author:  Haruue Icymoon                     *
// * Time:    Thu Oct 15 21:23:57 CST 2015       *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

import cn.com.caoyue.util.Time;

public class Example {
    public static void main(String[] args) {
        Time test1 = new Time();
        test1.set(2015, 9, 8, 9, 8, 57);  //使用 set() 定义 test1
        System.out.println(test1);  //调用 toString() 输出 test1
        System.out.println(test1.format("y年M月d日 HH:mm:ss"));  //格式化输出 test1

        Time test2 = new Time(test1);  //复制构造器定义 test2
        System.out.println(test2.equals(test1));  //时间相等判断 equals
        System.out.println(test2);  //调用 toString() 输出 test2

        System.out.println(test2.getTimeStamp()); //输出时间戳
        test2.setTimeZone("GMT");  //设置时区 +00:00
        System.out.println(test2);  //调用 toString() 输出更改时区后的 test2
        System.out.println(test2.getTimeStamp()); //输出时间戳，更改时区，时间戳不变
        test2.setTimeZone("CCT");  //设置时区 +08:00

        System.out.println(test2.getWeek());  //获得 test2 的星期
        System.out.println(test2.getWeekOfYear());  //获得 time2 的周数

        Time test3 = new Time(3, 5, 28, 43);  //使用构造器定义一个时间段 test3
        test2.add(test3);  //用 test2 加上这个时间段 test3
        System.out.println(test2);  //输出与 test3 相加后的 test2
        test2.minus(test1);  //用 test2 减去 test1
        System.out.println(test2.format("d天H小时m分s秒"));  //获取 test2 减去 test1 的结果，只考虑 d,H,m,s

        Time test4 = new Time(-1234567890);  //负数时间戳支持
        System.out.println(test4);  //输出负数时间戳定义的时间
    }
}
