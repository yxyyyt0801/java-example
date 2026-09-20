package com.sciatta.java.spring.tomcat.embed.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by yangxiaoyu on 2026/9/20<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * WebInitializer
 */
public class WebInitializer implements WebApplicationInitializer {  // SpringServletContainerInitializer + @HandlesTypes(WebApplicationInitializer.class) tomcat 容器负责触发
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
