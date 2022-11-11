package com.springboot.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class EditLessee {
    @Value("${lessee.name}")
    private String name;
    @Value("${lessee.newName}")
    private String newName;
    @Value("${lessee.newRemark}")
    private String newRemark;
    @Value("${lessee.newLogo}")
    private String newLogo;
    @Value("${lessee.serviceName}")
    private String serviceName;
    @Value("${lessee.newServiceName}")
    private String newServiceName;
}
