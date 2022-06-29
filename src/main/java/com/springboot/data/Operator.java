package com.springboot.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:operatorInfo.properties", encoding = "UTF-8")
public class Operator {
    @Value("${operator.loginName}")
    public String loginName;
    @Value("${operator.name}")
    public String name;
    @Value("${operator.code}")
    public String code;
    @Value("${operator.phone}")
    public String phone;
    @Value("${operator.email}")
    public String email;
    @Value("${operator.role}")
    public String role;
    @Value("${operator.remark}")
    public String remark;
    @Value("${operator.passWord}")
    public String passWord;
}
