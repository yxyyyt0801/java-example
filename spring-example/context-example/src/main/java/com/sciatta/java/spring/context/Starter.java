package com.sciatta.java.spring.context;

import com.sciatta.java.spring.context.message.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by yangxiaoyu on 2026/9/14<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * 启动类
 */
@ComponentScan
public class Starter {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Starter.class);
        Printer printer = context.getBean(Printer.class);
        printer.print();
    }
}
