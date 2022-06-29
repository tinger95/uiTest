package com.springboot.uitest.OperatorTest;

import com.springboot.bean.Browser;
import com.springboot.data.Lessee;
import com.springboot.data.Operator;
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

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, OperatorServiceImpl.class, Lessee.class, Operator.class})
public class AddOperatorTest extends AbstractTestNGSpringContextTests {
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
    Lessee lessee;
    @Autowired
    Operator operator;

    private String tempPWD;

    PageList pageList = new PageList();

    //新增操作员，登录名wutingting、姓名吴婷婷、工号1918wutt、电话18512345678、邮箱wutingting@ebupt.com、角色任选其一、备注
    @DataProvider(name = "operatorInfo")
    public Object[][] operatorInfo() {
        Map<String, String> map = operatorConfig();
        return new Object[][]{{map}};
    }

    public Map<String, String> operatorConfig() {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //登录名：wutingting+yyyyMMdd
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String nowTime = simpleDateFormat.format(System.currentTimeMillis());
            simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
            String remarkTime = simpleDateFormat.format(System.currentTimeMillis());
            map.put("lesseeName", "测试租户20220225");
            map.put("loginName", "wutingting" + nowTime);
            //姓名
            map.put("name", "吴婷婷");
            //工号
            map.put("code", "1918wutt");
            //电话号码：18511266861
            map.put("phone", "18511266861");
            //邮箱：wutingting@ebupt.com
            map.put("email", "wutingting@ebupt.com");
            //角色
            map.put("role", "全部权限");
            //增加备注，冒烟测试，新增操作员：操作员yyyyMMdd，时间：yyyy-MM-dd hh:mm:ss
            map.put("remark", "冒烟测试，新增操作员：操作员" + nowTime + "，时间：" + remarkTime);
            //密码
            map.put("passWord", "1qaz2wsx");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }

    @Test(priority = 1)
    public void start() {
        driver = browserService.startChrome();
    }

    @Test(priority = 2)
    public void login() throws InterruptedException, IOException {
        //登录系统
        loginService.login(driver, lessee.name, "admin", lessee.passWord);
        Thread.sleep(5000);
    }

    /**
     * 超级管理员新增租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 3)
    public void addOperator() throws InterruptedException, IOException {
        //进入系统管理-操作员管理页面，显示租户列表
        pageList.getList(driver, "系统管理", "操作员管理");

        //新增操作员，密码为返回值
        tempPWD = operatorService.addOperator(driver, operator);
        System.out.println(tempPWD);
        Thread.sleep(5000);
    }

    @Test(priority = 4, dependsOnMethods = "addOperator")
    public void logout() throws InterruptedException, IOException {
        //登录系统
        loginService.logout(driver);
        Thread.sleep(3000);
    }


    @Test(priority = 5, dependsOnMethods = "logout")
    public void operatorLogin() throws InterruptedException, IOException {
        loginService.login(driver, lessee.name, operator.loginName, tempPWD);
        Thread.sleep(3000);
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(driver);
        Thread.sleep(3000);
    }

    @Test(priority = 6, dependsOnMethods = "operatorLogin")
    public void end() {
        browserService.quitDriver();
    }

}