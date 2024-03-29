package com.springboot.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class SystemAdmin {
    @Value("${systemAdmin.lesseeName}")
    private String lesseeName;
    @Value("${systemAdmin.userName}")
    private String userName;
    @Value("${systemAdmin.passWord}")
    private String passWord;
}
