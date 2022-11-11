package com.springboot.remote;

import com.springboot.data.Role;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public void addRole(WebDriver driver, Role role) throws InterruptedException {
        //点击新增
        WebElement appendButton = driver.findElement(By.xpath("//*[@class='operate-wrapper']//span[text()='新增']/.."));
        appendButton.click();
        Thread.sleep(1000);

        //输入角色名称
        WebElement name = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input[@id='name']"));
        name.sendKeys(role.getName());
        Thread.sleep(1000);
        //输入备注
        WebElement remark = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//textarea[@id='remark']"));
        remark.sendKeys(role.getRemark());
        Thread.sleep(1000);

        //权限全选
        WebElement permission = driver.findElement(By.xpath("//div[@class='menuCard']//span[@class='ant-checkbox']"));
        permission.click();

        WebElement submit = driver.findElement(By.xpath("//*[@class='ant-modal-footer']//button[@class='ant-btn ant-btn-primary']"));
        submit.click();
        Thread.sleep(1000);
    }
}
