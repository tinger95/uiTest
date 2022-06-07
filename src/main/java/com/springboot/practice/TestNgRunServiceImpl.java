package com.springboot.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestNgRunServiceImpl implements TestNgRunService{

    @Autowired
    private Config config;
    @Override
    public String runAll() {
        System.out.println("执行中...");
        return config.url;
    }
}
