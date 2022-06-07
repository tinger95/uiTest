package com.springboot.remote;

import org.openqa.selenium.WebDriver;

public interface BrowserService {
    /**
     * 打开浏览器
     * @return
     */
    public WebDriver startChrome();
    /**
     * 关闭浏览器
     */
    public void quitDriver();
    /**
     * 关闭当前窗口
     */
    public void closeDriver();
}
