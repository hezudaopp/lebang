package com.youmayon.lebang.config;

import com.youmayon.lebang.Env;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * 环境变量设置properties
 * Created by Jawinton on 17/3/3.
 */
@Configuration
public class PropertySourcesConfig {
    private static final Resource[] PROPERTIES = propertyFilenames(Env.ENV);

    private static ClassPathResource[] propertyFilenames(String env) {
        ClassPathResource classPathResource = new ClassPathResource("/" + env);
        String[] propertyFilenames = null;
        try {
            propertyFilenames = classPathResource.getFile().list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".properties");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (propertyFilenames == null) {
            return null;
        }
        ClassPathResource[] classPathResources = new ClassPathResource[propertyFilenames.length];
        for (int i = 0; i < propertyFilenames.length; i++) {
            classPathResources[i] = new ClassPathResource(env + "/" + propertyFilenames[i]);
        }
        return classPathResources;
    }

    public static class Config {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            pspc.setLocations(PROPERTIES);
            return pspc;
        }
    }
}
