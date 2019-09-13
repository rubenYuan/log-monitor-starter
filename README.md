# 天鹰(LogMonitor)

#一、简介

##1.1、目标、定位

    该项目孵化于日志监控基础之上，旨在为开发快速定位问题，从而解决问题，达到快速迭代的目标。

##1.2、功能描述

    全局日志处理
    采用了注解+aop结合的方式，使得业务方开箱即用，无缝对接，实现了日志灵活打印功能，包括请求的入参，出参等，以及兼容各个请求方式，get/post。
   ![Image_text](https://img-blog.csdnimg.cn/20190913115226590.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3J1YmVuOTUwMDE=,size_16,color_FFFFFF,t_70)
    
    error级别日志报警
    报警钉钉
   ![Image text](https://img-blog.csdnimg.cn/20190912223045719.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3J1YmVuOTUwMDE=,size_16,color_FFFFFF,t_70)
    报警邮件
   ![Image text](https://img-blog.csdnimg.cn/20190912223109434.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3J1YmVuOTUwMDE=,size_16,color_FFFFFF,t_70)

#二、服务

##2.1、项目版本

            <dependency>
                <groupId>com.xiu</groupId>
                <artifactId>log-monitor-starter</artifactId>
                <version>0.0.2-SNAPSHOT</version>
            </dependency>
    
 
##2.2、接入方式
    依赖引入这个stater,logback.xml拦截error级别的日志，重写ErrorLayout方法。开启接口日志打印，捕获日志输入，输出

##三、感谢赞助
![Image text](https://img-blog.csdnimg.cn/20190913114759984.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3J1YmVuOTUwMDE=,size_16,color_FFFFFF,t_70)
![Image text](https://img-blog.csdnimg.cn/20190913114830413.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3J1YmVuOTUwMDE=,size_16,color_FFFFFF,t_70)

 
 
 


