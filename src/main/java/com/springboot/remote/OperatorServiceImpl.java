package com.springboot.remote;

import com.springboot.data.Operator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService{

    //临时密码登录，修改密码
    @Override
    public void updatePassWord(WebDriver driver) throws InterruptedException {
        WebElement questionElement = driver.findElement(By.xpath("//*[@class='ant-modal-confirm-body']/span[text()='是否修改密码']"));
        if (questionElement.equals("") || questionElement == null) {
            return;
        }

        //点击是，确定修改密码
        WebElement confirmElement = driver.findElement(By.xpath("//*[@class='ant-modal-confirm-btns']//button[@class='ant-btn ant-btn-danger']"));
        confirmElement.click();
        Thread.sleep(2000);

        //输入新密码、确认密码
        WebElement newPassWord = driver.findElement(By.id("newPassword"));
        newPassWord.sendKeys("1qaz2wsx");
        Thread.sleep(2000);
        WebElement confirm = driver.findElement(By.id("confirm"));
        confirm.sendKeys("1qaz2wsx");
        Thread.sleep(2000);

        WebElement submitElement = driver.findElement(By.xpath("//*[@class='ant-modal-content']//button[@class='ant-btn ant-btn-primary']"));
        submitElement.click();
    }

    @Override
    public String addOperator(WebDriver driver, Operator operatorInfo) throws InterruptedException {
        //点击新增
        WebElement appendButton = driver.findElement(By.xpath("//*[@class='operate-wrapper']//span[text()='新增']/.."));
        appendButton.click();
        Thread.sleep(1000);

        WebElement loginName = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input[@id='loginName']"));
        loginName.sendKeys(operatorInfo.loginName);
        Thread.sleep(1000);

        WebElement name = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input[@id='name']"));
        name.sendKeys(operatorInfo.name);
        Thread.sleep(1000);

        WebElement code = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input[@id='code']"));
        code.sendKeys(operatorInfo.code);
        Thread.sleep(1000);

        WebElement phone = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input[@id='phone']"));
        phone.sendKeys(operatorInfo.phone);
        Thread.sleep(1000);

        WebElement email = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input[@id='email']"));
        email.sendKeys(operatorInfo.email);
        Thread.sleep(1000);

        WebElement role = driver.findElement(By.xpath("//*[@role='combobox']//div[text()='请选择角色']"));
        role.click();
        Thread.sleep(2000);
        //角色选择
        List<WebElement> roleSelect = driver.findElements(By.xpath("//*[@class='ant-select-dropdown-content']//li"));
        for (WebElement element:roleSelect){
            if (element.getText().equals(operatorInfo.role)){
                element.click();
            }
        }
        Thread.sleep(1000);

        WebElement remark = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//textarea[@id='remark']"));
        remark.sendKeys(operatorInfo.remark);
        Thread.sleep(1000);

        WebElement submit = driver.findElement(By.xpath("//*[@class='ant-modal-footer']//button[@class='ant-btn ant-btn-primary']"));
        submit.click();
        Thread.sleep(5000);

        //复制密码
        String passWord;
        List<WebElement> operatorMessage = driver.findElements(By.xpath("//*[@class='ant-modal-confirm-content']//span"));
        passWord = operatorMessage.get(6).getText();
        WebElement copy = driver.findElement(By.xpath("//*[@class='ant-modal-confirm-btns']/button"));
        copy.click();
        Thread.sleep(1000);

        return passWord;
    }

    @Override
    public void searchOperator(WebDriver driver, String searchType, String searchValue) throws InterruptedException {
        //点击请选择
        WebElement role = driver.findElement(By.xpath("//*[@role='combobox']//div[text()='请选择']"));
        role.click();
        Thread.sleep(1000);
        //查询类型选择
        List<WebElement> searchSelect = driver.findElements(By.xpath("//*[@class='ant-select-dropdown-content']//li"));
        for (WebElement element:searchSelect){
            if (element.getText().equals(searchType)){
                element.click();
            }
        }
        Thread.sleep(1000);
        //输入查询信息
        WebElement searchInput = driver.findElement(By.xpath("//*[@class='ant-form-item-children']//input"));
        searchInput.sendKeys(searchValue);
        Thread.sleep(1000);
        //点击查询
        WebElement submit = driver.findElement(By.xpath("//*[@class='table-page-search-submitButtons']/button[@class='ant-btn ant-btn-primary']"));
        submit.click();
        Thread.sleep(1000);
    }
}
