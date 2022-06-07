package com.springboot.remote;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public void addRole(WebDriver driver, Map<String, String> roleInfo) throws InterruptedException {
        //点击新增
        WebElement appendButton = driver.findElement(By.xpath("//*[@class='operate-wrapper']//span[text()='新增']/.."));
        appendButton.click();
        Thread.sleep(1000);

        WebElement name = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input[@id='name']"));
        name.sendKeys(roleInfo.get("name"));
        Thread.sleep(1000);

        WebElement remark = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//textarea[@id='remark']"));
        remark.sendKeys(roleInfo.get("remark"));
        Thread.sleep(1000);

        List<WebElement> permissionList = driver.findElements(By.xpath("//*[@class='ant-modal-content']//div[@class='ant-tabs-nav ant-tabs-nav-animated']/div/div"));
        for (WebElement element : permissionList) {
            element.click();
            WebElement pageAll = driver.findElement(By.xpath("//*[@class='ant-checkbox-wrapper']/span[text()='此页全选']/../span[@class='ant-checkbox']"));
            pageAll.click();
            Thread.sleep(2000);
        }

        WebElement submit = driver.findElement(By.xpath("//*[@class='ant-modal-footer']//button[@class='ant-btn ant-btn-primary']"));
        submit.click();
        Thread.sleep(1000);
    }
}
