package com.springboot.remote;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageList {
    public void getList(WebDriver driver, String firstMenu, String secondMenu) throws InterruptedException {
        //点击一级菜单运营管理
        WebElement operationMenu = driver.findElement(By.xpath("//*[@class='nav-wrapper-dms']/ul/li/span[text()='"+firstMenu+"']/parent::*"));
        operationMenu.click();
        operationMenu.click();
        Thread.sleep(2000);

        //点击二级菜单租户管理
        WebElement lesseeMenu = driver.findElement(By.xpath("//*[@class='lu-menu lu-menu__side lu-menu__light']/ul/li/span[text()='"+secondMenu+"']/parent::*"));
        lesseeMenu.click();
        Thread.sleep(3000);
    }
}
