package com.springboot.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class Browser {
    @Value("${driver.url}")
    private String url;
    @Value("${driver.location}")
    private String location;
}
