package com.springboot.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class Browser {
    @Value("${driver.url}")
    public String url;
    @Value("${driver.location}")
    public String location;
}
