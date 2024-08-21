package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class WebConfig {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Bean
    public ViewResolver viewResolver() {
        CustomViewResolver resolver = new CustomViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setOrder(1);
        return resolver;
    }
}