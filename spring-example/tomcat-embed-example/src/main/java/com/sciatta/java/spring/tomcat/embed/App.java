package com.sciatta.java.spring.tomcat.embed;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Created by yangxiaoyu on 2026/9/20<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * App
 */
@Slf4j
public class App {

    public static void main(String[] args) throws LifecycleException {
        // 创建Tomcat启动器
        Tomcat tomcat = new Tomcat();

        // 初始化工作目录
        setDir(tomcat);

        // 设置默认HTTP连接器端口号
        tomcat.setPort(8080);

        // 创建并添加默认 HTTP 连接器，必须调用
        tomcat.getConnector();

        // 启动Tomcat
        tomcat.start();
    }

    private static void setDir(Tomcat tomcat) {
        // 设置 Tomcat 基础目录为临时目录下的 tomcat-embed
        String tempDir = System.getProperty("java.io.tmpdir");
        log.info("临时目录：{}", tempDir);

        File baseDir = new File(tempDir, "tomcat-embed");
        tomcat.setBaseDir(baseDir.getAbsolutePath());

        // 创建临时目录下的 web 应用目录，比如 webapps/ROOT
        File appDir = new File(baseDir, "webapps/ROOT");
        if (!appDir.exists()) {
            boolean mkdirs = appDir.mkdirs();
            log.info("创建 web 应用目录：{}, 结果：{}", appDir.getAbsolutePath(), mkdirs);
        }
        tomcat.addWebapp("", appDir.getAbsolutePath());
    }
}
