package com.springboot.uitest;

import com.springboot.bean.Browser;
import com.springboot.data.Lessee;
import com.springboot.data.SystemAdmin;
import com.springboot.remote.*;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, OperatorServiceImpl.class, SystemAdmin.class, Lessee.class})
public class AddLesseeTest extends AbstractTestNGSpringContextTests {
    public WebDriver driver;
    @Autowired
    BrowserService browserService;
    @Autowired
    LoginService loginService;
    @Autowired
    LesseeService lesseeService;
    @Autowired
    OperatorService operatorService;
    @Autowired
    SystemAdmin systemAdmin;
    @Autowired
    Lessee lessee;

    private String tempPWD;

    PageList pageList = new PageList();

    @DataProvider(name = "lesseeInfo")
    public Object[][] lesseeInfo() {
        Map<String, String> map = lesseeConfig();
        return new Object[][]{{map}};
    }

    public Map<String, String> lesseeConfig() {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //租户名称，测试租户+yyyyMMdd
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String nowTime = simpleDateFormat.format(System.currentTimeMillis());
            simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
            String remarkTime = simpleDateFormat.format(System.currentTimeMillis());
            map.put("lesseeName", "测试租户" + nowTime);
            //增加备注，冒烟测试，超级管理员新增租户：测试租户yyyyMMdd，时间：yyyy-MM-dd hh:mm:ss
            map.put("remark", "冒烟测试，超级管理员新增租户：测试租户" + nowTime + "，时间：" + remarkTime);
            //logo，E:\工作记录\BDPS\图片
            map.put("logo", "E:\\工作记录\\BDPS\\图片\\logo1.png");
            //电话号码：18511266861
            map.put("telephone", "18511266861");
            //邮箱：wutingting@ebupt.com
            map.put("email", "wutingting@ebupt.com");
            //密码
            map.put("passWord", "1qaz2wsx");
            //勾选全部服务
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }

    @Test(priority = 1)
    public void start() {
        driver = browserService.startChrome();
    }


    /**
     * admin登录系统租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 2)
    public void login() throws InterruptedException, IOException {
        //登录系统
        loginService.login(driver, systemAdmin.lesseeName, systemAdmin.userName, systemAdmin.passWord);
        Thread.sleep(3000);
    }

    /**
     * 超级管理员新增租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 3)
    public void addLessee() throws InterruptedException, IOException {
        //进入运营管理-租户管理页面，显示租户列表
        pageList.getList(driver, "运营管理", "租户管理");

        //新增租户：测试租户yyyyMMdd，密码为返回值
        tempPWD = lesseeService.addLessee(driver);
        System.out.println(tempPWD);
        Thread.sleep(5000);
    }

    /**
     * 退出登录
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4, dependsOnMethods = "addLessee")
    public void logout() throws InterruptedException, IOException {
        loginService.logout(driver);
        Thread.sleep(1000);
    }

    /**
     * 租户管理员登录租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 5, dependsOnMethods = "logout")
    public void lesseeLogin() throws InterruptedException, IOException {
        //登录系统
        loginService.login(driver, lessee.name, "admin", tempPWD);
        Thread.sleep(3000);
    }

    /**
     * 修改密码为1qaz2wsx
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 6, dependsOnMethods = "lesseeLogin")
    public void lesseeAdminLogin() throws InterruptedException, IOException {
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(driver);
        Thread.sleep(3000);
    }

    @Test(priority = 7, dependsOnMethods = "lesseeAdminLogin")
    public void end() {
        browserService.quitDriver();
    }

}