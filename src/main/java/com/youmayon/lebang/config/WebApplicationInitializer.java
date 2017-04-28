package com.youmayon.lebang.config;

import com.youmayon.lebang.Env;
import com.youmayon.lebang.filter.SimpleCORSFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.nio.charset.StandardCharsets;

/**
 * Web服务初始化配置，加载配置文件
 * Created by Jawinton on 16/12/13.
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    public String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    public Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    public Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    /**
     * 跨域支持
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("spring.profiles.default", Env.ENV);
        FilterRegistration.Dynamic simpleCORSFilter = servletContext.addFilter("simpleCORSFilter", SimpleCORSFilter.class);
        simpleCORSFilter.setInitParameter("encoding", String.valueOf(StandardCharsets.UTF_8));
        simpleCORSFilter.addMappingForUrlPatterns(null, false, "/*");
        super.onStartup(servletContext);
    }
}
