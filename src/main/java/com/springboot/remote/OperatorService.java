package com.springboot.remote;

import com.springboot.data.Operator;
import org.openqa.selenium.WebDriver;

public interface OperatorService {
    public void updatePassWord(WebDriver driver) throws InterruptedException;
    public String addOperator(WebDriver driver, Operator operatorInfo) throws InterruptedException;
    public void searchOperator(WebDriver driver, String searchType, String searchValue) throws InterruptedException;
}
