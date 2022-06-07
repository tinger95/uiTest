package com.springboot.remote;

import org.openqa.selenium.WebDriver;

public interface LoginService {
    public void login(WebDriver driver, String lesseeName, String userName, String passWord) throws InterruptedException;
    public void logout(WebDriver driver) throws InterruptedException;
}
