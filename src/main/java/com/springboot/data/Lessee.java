package com.springboot.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class Lessee {
    @Value(value = "${lessee.name}")
    private String name;
    @Value("${lessee.remark}")
    private String remark;
    @Value("${lessee.logo}")
    private String logo;
    @Value("${lessee.adminName}")
    private String adminName;
    @Value("${lessee.telephone}")
    private String telephone;
    @Value("${lessee.email}")
    private String email;
    @Value("${lessee.passWord}")
    private String passWord;
    @Value("${lessee.serviceName}")
    private String serviceName;

}
