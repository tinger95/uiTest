package com.springboot.remote;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LesseeService {
    public String addLessee(WebDriver driver) throws InterruptedException, IOException;
}
