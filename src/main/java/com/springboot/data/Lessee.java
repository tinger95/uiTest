package com.springboot.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:lessee.properties", encoding = "UTF-8")
public class Lessee {
    @Value("${lessee.name}")
    public String name;
    @Value("${lessee.remark}")
    public String remark;
    @Value("${lessee.logo}")
    public String logo;
    @Value("${lessee.adminName}")
    public String adminName;
    @Value("${lessee.telephone}")
    public String telephone;
    @Value("${lessee.email}")
    public String email;
    @Value("${lessee.passWord}")
    public String passWord;
}
