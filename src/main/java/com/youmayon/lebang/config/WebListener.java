package com.youmayon.lebang.config;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.Properties;

/**
 * ServlectContext监听器
 * Created by Jawinton on 2017/3/6.
 */
@javax.servlet.annotation.WebListener
public class WebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // set logging properties file of different environment.
        ServletContext servletContext = servletContextEvent.getServletContext();
        String env = servletContext.getInitParameter("spring.profiles.active");
        if (env == null) {
            env = servletContext.getInitParameter("spring.profiles.default");
        }
        String basePath = WebListener.class.getResource("/").getPath();
        String log4jPropertiesPath = basePath + env + "/log4j.properties";
        Properties properties = new Properties();
        servletContext.log("Initializing log4j from [" + log4jPropertiesPath + "]");
        File propertiesFile = new File(log4jPropertiesPath);
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(propertiesFile));
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            servletContext.log(e.getMessage(), e);
        }
        PropertyConfigurator.configure(properties);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
