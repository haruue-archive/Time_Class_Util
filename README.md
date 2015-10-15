Time Class Util
============

##Overview
用 Java 写的一个 Time 类，支持时间戳转换、星期换算、格式化时间解析和输出、时区和并不完善的时间加减功能。<br />
功能上主要是实现朱大 [@Jude95](https://github.com/Jude95) 提供的 ITime 接口。

##Document
由于时间仓促，没有写源代码里的 javadoc 注释了，先在这里进行一些说明，以后再补充到源代码里去好了。~~(因为反正也是自己用所以感觉这句话多半会坑掉)~~<br />
<ul>
    <li><code>getWeek()</code> 的返回值中， 0 代表周日;</li>
    <li><code>getWeekOfYear()</code> 的每年第一个星期指的是每年第一个星期一所在的星期;</li>
    <li><code>add(ITime data)</code> 的 <code>date</code> 必须是时间段，就是定义 <code>day, hour, minute, second</code> 的时间，<code>year, month</code> 即使定义了也会被无视掉，为了方便，使用构造器定义对象时，可直接使用 <code>Time(int day, int hour, int minute, int second)</code> 或者时间戳 <code>Time(long timeStamp)</code> （时间戳长度不要大于一个月）来定义一个对象;</li>
    <li><code>minus(ITime data)</code> 的 <code>data</code> 必须是另一个时间点，并且这个时间点在 <code>this</code> 表示的时间点的前面，函数的作用是产生一个时间段，你可以用 <code>getTimeStamp()</code> 或者 <code>format(String format)</code> 函数以及其他以 get 开头的函数来获取这个时间段 ，不建议在获得这个时间段之后继续对这个对象进行其他 set 类的操作;</li>
    <li><code>format(String format)</code> 以及 <code>parse(String time, String format)</code> 中的 <code>format</code> 的详细说明:
        <ul>
            <li>对于 <code>parse(String time, String format)</code> 函数，单字母形式和多字母形式是等价的，如 <code>m</code> 与 <code>mm</code> 甚至是 <code>mmmmmmmmm</code> 都是等价的; </li>
            <li>对于 <code>parse(String time, String format)</code> 函数，参数与参数之间必须有非数字字符间隔，类似于 <code>"20151015230000"</code> 是无法用 <code>"yyyyMMddHHmmss"</code> 进行解析的;</li>
            <li>对于 <code>format(String format)</code> 函数， <code>y</code> 和 <code>yyyy</code> 是等价的; </li>
            <li>对于 <code>format(String format)</code> 函数， <code>MM</code> 与 <code>M</code>，<code>dd</code> 与 <code>d</code>， <code>HH</code> 与 <code>H</code>，<code>mm</code> 与 <code>m</code>，<code>ss</code> 与 <code>s</code>这种有一个字母和两个字母区分的形式，两个字母表示如果这一位不满两位数则补 0 ，一个字母则这种情况不补 0 ;</li>
            <li>对于 <code>format(String format)</code> 函数， <code>hh</code> 所表示的 12 小时制，范围是 0 到 11 ，而不是 1 到 12 。
        </ul>
</ul>

##Known BUG & Troubleshooting
由于暂时没有进行充分的调试，当前没有发现 BUG ，如果你发现了新的 BUG ，欢迎提交 [issue](https://github.com/haruue/Time_Class_Util/issues) ，万分感谢 !

##Developed By
Haruue Icymoon <haruue@caoyue.com.cn> <br />
本项目作为 红岩网校 - 移动开发部 的第 2 次作业 的 Level 4 部分。

<!--
// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 2 (20151011)          *
// * Level 4 - Make a Class for Time Convert     *
// * Author:  Haruue Icymoon                     *
// * Time:    Thu Oct 15 21:23:57 CST 2015       *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *
-->
