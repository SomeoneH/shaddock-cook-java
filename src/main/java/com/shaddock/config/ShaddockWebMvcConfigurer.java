package com.shaddock.config;

import com.shaddock.config.handler.TokenToUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class ShaddockWebMvcConfigurer extends WebMvcConfigurationSupport {
    @Autowired
    private TokenToUserMethodArgumentResolver tokenToUserMethodArgumentResolver;

    /**
     * @param argumentResolvers
     * @tip @tokenToUserMethodArgumentResolver 注解处理方法
     */
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenToUserMethodArgumentResolver);
    }
}
