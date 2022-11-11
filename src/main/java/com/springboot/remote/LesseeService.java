package com.springboot.remote;

import com.springboot.data.EditLessee;
import com.springboot.data.Lessee;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LesseeService {
    public String addLessee(WebDriver driver, Lessee lessee) throws InterruptedException, IOException;
    public void searchLessee(WebDriver driver, String lesseeName) throws InterruptedException;
    public void checkLessee(WebDriver driver, String lesseeName) throws InterruptedException;
    public void editLessee(WebDriver driver, EditLessee editLessee) throws InterruptedException, IOException;
    public void deleteLessee(WebDriver driver, String lesseeName) throws InterruptedException;
    public String updatePwdLessee(WebDriver driver, String lesseeName) throws InterruptedException;

}
