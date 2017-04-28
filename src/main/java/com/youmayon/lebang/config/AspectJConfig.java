package com.youmayon.lebang.config;

import com.youmayon.lebang.aspects.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 切面配置类
 * Created by Jawinton on 17/3/3.
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectJConfig {
    /**
     * 日志切面类
     * @return
     */
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
