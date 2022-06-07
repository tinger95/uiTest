package com.springboot.remote;

import org.openqa.selenium.WebDriver;

import java.util.Map;

public interface RoleService {
    public void addRole(WebDriver driver, Map<String,String> roleInfo) throws InterruptedException;
}
