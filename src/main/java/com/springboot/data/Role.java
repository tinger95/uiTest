package com.springboot.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class Role {
    @Value("${role.name}")
    private String name;
    @Value("${role.remark}")
    private String remark;
}
