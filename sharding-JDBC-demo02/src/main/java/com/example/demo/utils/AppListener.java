package com.example.demo.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2018/10/17.
 */

@WebListener
public class AppListener implements ServletContextListener {

    public static String aaa;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext sc = sce.getServletContext();
            String profiles = sc.getInitParameter("spring.profiles.active");
            aaa = profiles;
        } catch (Exception e) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}