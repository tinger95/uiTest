package com.springboot.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:systemAdmin.properties", encoding = "UTF-8")
public class SystemAdmin {
    @Value("${systemAdmin.lesseeName}")
    public String lesseeName;
    @Value("${systemAdmin.userName}")
    public String userName;
    @Value("${systemAdmin.passWord}")
    public String passWord;
}
