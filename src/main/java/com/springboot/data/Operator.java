package com.springboot.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class Operator {
    @Value("${operator.loginName}")
    private String loginName;
    @Value("${operator.name}")
    private String name;
    @Value("${operator.code}")
    private String code;
    @Value("${operator.phone}")
    private String phone;
    @Value("${operator.email}")
    private String email;
    @Value("${operator.role}")
    private String role;
    @Value("${operator.remark}")
    private String remark;
    @Value("${operator.passWord}")
    private String passWord;
}
