package com.springboot.remote;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LesseeService {
    public String addLessee(WebDriver driver) throws InterruptedException, IOException;
    public void searchLessee(WebDriver driver, String lesseeName) throws InterruptedException;
    public void checkLessee(WebDriver driver, String lesseeName) throws InterruptedException;
    public void editLessee(WebDriver driver, String lesseeName, String lesseeNewName, String remark, String logo) throws InterruptedException, IOException;
    public void deleteLessee(WebDriver driver, String lesseeName) throws InterruptedException;
}
