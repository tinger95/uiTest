package com.springboot.remote;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{
    @Override
    public void login(WebDriver driver, String lesseeName, String userName, String passWord) throws InterruptedException {
        Thread.sleep(2000);
        WebElement lessee = driver.findElement(By.className("ant-select-selection__placeholder"));
        lessee.click();
        Thread.sleep(2000);
        //租户选择
        List<WebElement> lesseeSelect = driver.findElements(By.xpath("//*[@class='ant-select-dropdown-content']//li"));
        for (WebElement element:lesseeSelect){
            if (element.getText().equals(lesseeName)){
                element.click();
            }
        }
        Thread.sleep(1000);

        //登录名
        WebElement userNameElement = driver.findElement(By.id("username"));
        userNameElement.sendKeys(userName);
        Thread.sleep(1000);

        //密码
        WebElement passWordElement = driver.findElement(By.id("password"));
        passWordElement.sendKeys(passWord);
        Thread.sleep(1000);

        //修改gateway配置文件false，不显示验证码跳过验证码
        WebElement captcha = driver.findElement(By.id("captcha"));
        captcha.sendKeys("123456");
        Thread.sleep(1000);

        //点击登录
        WebElement login = driver.findElement(By.xpath("//*[@class='ant-form-item-control']/span/button"));
        login.click();
    }

    @Override
    public void logout(WebDriver driver) throws InterruptedException {
        WebElement logout = driver.findElement(By.xpath("//*[@class='header-right']//span[@title='退出']"));
        logout.click();
        Thread.sleep(1000);
        WebElement confirm = driver.findElement(By.xpath("//*[@class='ant-modal-confirm-btns']//button[@class='ant-btn ant-btn-danger']"));
        confirm.click();
        Thread.sleep(1000);
    }

}
