package com.springboot.remote;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RoleManager {

    public void addRole(WebDriver driver) throws InterruptedException {
        //点击新增
        WebElement appendButton = driver.findElement(By.xpath("//*[@class='operate-wrapper']/button"));
        appendButton.click();
        Thread.sleep(1000);

        //角色名称：全部权限
        WebElement roleName = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input[@id='name']"));
        roleName.sendKeys("全部权限");
        WebElement remark = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//textarea[@id='remark']"));
        remark.sendKeys("新建角色，名为全部权限");

    }
}
