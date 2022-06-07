package com.springboot.remote;

import com.springboot.bean.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BrowserServiceImpl implements BrowserService{
    @Autowired
    Browser browser;
    public static WebDriver driver;

    //启动chrome浏览器
    @Override
    public WebDriver startChrome() {
        try {
            System.setProperty("webdriver.chrome.driver", browser.location);
            //清理缓存
            //ClearTemp.clearTempFile();
            driver = new ChromeDriver();
            driver.get(browser.url);
            //窗口默认最大化
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("打开谷歌浏览器报错" + e);
        }
        System.out.println("成功打开浏览器！");
        return driver;
    }

    //关闭浏览器
    @Override
    public void quitDriver() {
        try {
            Thread.sleep(2000);
            driver.quit();
            System.out.println("成功关闭浏览器！");
        } catch (Exception e) {
            System.out.println("关闭浏览器报错" + e);
        }
    }

    //关闭当前窗口
    @Override
    public void closeDriver() {
        try {
            Thread.sleep(2000);
            driver.close();
            System.out.println("成功当前窗口！");
        } catch (Exception e) {
            System.out.println("关闭当前窗口报错" + e);
        }
    }
}
