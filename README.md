Time Class Util for Android
============

##Overview
用 Java 写的一个 Time 类，支持时间戳转换、星期换算、格式化时间解析和输出、时区和并不完善的时间加减功能。<br />
功能上主要是实现朱大 [@Jude95](https://github.com/Jude95) 提供的 ITime 接口。

##Dependencie
在依赖里添加
``` shell
compile 'cn.com.caoyue.util:time:1.2.1'
```

##Javadoc
http://haruue.github.io/Time_Class_Util

##Something Important

+ `format(String format)` 以及 `parse(String time, String format)` 中的 `format` 的详细说明:
    
    + 对于 `parse(String time, String format)` 函数，单字母形式和多字母形式是等价的，如 `m` 与 `mm` 甚至是 `mmmmmmmmm` 都是等价的; 
    + 对于 `parse(String time, String format)` 函数，参数与参数之间必须有非数字字符间隔，类似于 `"20151015230000"` 是无法用 `"yyyyMMddHHmmss"` 进行解析的;
    + 对于 `format(String format)` 函数， `y` 和 `yyyy` 是等价的; 
    + 对于 `format(String format)` 函数， `MM` 与 `M`，`dd` 与 `d`， `HH` 与 `H`，`mm` 与 `m`，`ss` 与 `s`这种有一个字母和两个字母区分的形式，两个字母表示如果这一位不满两位数则补 0 ，一个字母则这种情况不补 0 ;

##Known BUG & Troubleshooting
由于暂时没有进行充分的调试，当前没有发现 BUG ，如果你发现了新的 BUG ，欢迎提交 [issue](https://github.com/haruue/Time_Class_Util/issues) ，万分感谢 !

##Developed By
Haruue Icymoon <haruue@caoyue.com.cn> <br />
本项目作为 红岩网校 - 移动开发部 的第 2 次作业 的 Level 4 部分。

##License
Apache License 2.0

<!--
// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 2 (20151011)          *
// * Level 4 - Make a Class for Time Convert     *
// * Author:  Haruue Icymoon                     *
// * Time:    Thu Oct 15 21:23:57 CST 2015       *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *
-->
