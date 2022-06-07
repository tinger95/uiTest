package com.springboot.remote;

import com.springboot.data.Lessee;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class LesseeServiceImpl implements LesseeService {
    @Autowired
    Lessee lessee;

    @Override
    public String addLessee(WebDriver driver) throws InterruptedException, IOException {
        //点击新增
        WebElement appendButton = driver.findElement(By.xpath("//*[@class='operate-wrapper']//span[text()='新增']/.."));
        appendButton.click();
        Thread.sleep(1000);

        //租户名称，测试租户+yyyyMMdd
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String nowTime = simpleDateFormat.format(System.currentTimeMillis());
        WebElement name = driver.findElement(By.xpath("//*[@class='ant-form-item-children']/span/input[@id='name']"));
        name.sendKeys(lessee.name);
        Thread.sleep(1000);

        //增加备注
        WebElement remarks = driver.findElement(By.xpath("//*[@class='ant-form-item-children']/textarea[@id='remarks']"));
        remarks.sendKeys(lessee.remark);
        Thread.sleep(1000);

        //利用AutoIT3实现logo上传
        WebElement upload = driver.findElement(By.className("ant-upload"));
        upload.click();
        Thread.sleep(1000);
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("E:\\picture\\upload.exe" + " " + "chrome" + " " + "E:\\picture\\logo.png");
        Thread.sleep(1000);

        //租户管理员姓名
        WebElement clear = driver.findElement(By.xpath("//*[@id='adminName']/../span/i"));
        clear.click();
        WebElement adminName = driver.findElement(By.id("adminName"));
        adminName.sendKeys( lessee.adminName);
        Thread.sleep(1000);

        //电话号码：18511266861
        WebElement adminTelephone = driver.findElement(By.id("adminTelephone"));
        adminTelephone.sendKeys(lessee.telephone);
        Thread.sleep(1000);

        //邮箱：wutingting@ebupt.com
        WebElement adminMail = driver.findElement(By.id("adminMail"));
        adminMail.sendKeys(lessee.email);
        Thread.sleep(1000);

        //勾选服务项
        WebElement workOrder = driver.findElement(By.xpath("//div[@value='工单管理']//input"));
        workOrder.click();
        WebElement dataSource = driver.findElement(By.xpath("//div[@value='数据源']//input"));
        dataSource.click();
        Thread.sleep(1000);

        //勾选idox全部服务
        WebElement idox = driver.findElement(By.xpath("//tr[@data-row-key='2']/td/a/button"));
        idox.click();

        List<WebElement> idoxList = driver.findElements(By.xpath("//tr[@data-row-key='2-extra-row']//input"));
        for (WebElement element : idoxList) {
            element.click();
        }
        Thread.sleep(1000);

        //确定提交
        WebElement submit = driver.findElement(By.xpath("//*[@class='ant-modal-footer']//button[@class='ant-btn ant-btn-primary']"));
        submit.click();
        Thread.sleep(5000);

        //复制密码
        String passWord;
        List<WebElement> adminMessage = driver.findElements(By.xpath("//*[@class='ant-modal-confirm-content']//span"));
        passWord = adminMessage.get(6).getText();
        WebElement copy = driver.findElement(By.xpath("//*[@class='ant-modal-confirm-btns']/button"));
        copy.click();
        Thread.sleep(1000);

        return passWord;
    }
}
