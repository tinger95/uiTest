package com.springboot.remote;

import com.springboot.data.Opetator;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public interface OperatorService {
    public void updatePassWord(WebDriver driver) throws InterruptedException;
    public String addOperator(WebDriver driver, Opetator operatorInfo) throws InterruptedException;
}
