package com.springboot.uitest;

import com.springboot.bean.Browser;
import com.springboot.data.Lessee;
import com.springboot.remote.*;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, RoleServiceImpl.class, Lessee.class})
public class AddRoleTest extends AbstractTestNGSpringContextTests {
    public WebDriver driver;
    @Autowired
    BrowserService browserService;
    @Autowired
    LoginService loginService;
    @Autowired
    RoleService roleService;
    @Autowired
    Lessee lessee;

    PageList pageList = new PageList();

    //新增角色，角色名称：全部权限，备注，权限全选
    @DataProvider(name = "roleInfo")
    public Object[][] roleInfo() {
        Map<String, String> map = roleConfig();
        return new Object[][]{{map}};
    }

    public Map<String, String> roleConfig() {
        Map<String, String> map = new HashMap<String, String>();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
            String nowTime = simpleDateFormat.format(System.currentTimeMillis());
            String remarkTime = simpleDateFormat.format(System.currentTimeMillis());
            map.put("name", "全部权限0518");
            //增加备注，冒烟测试，新增操作员：操作员yyyyMMdd，时间：yyyy-MM-dd hh:mm:ss
            map.put("remark", "冒烟测试，新增角色：全部角色，时间：" + remarkTime);
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

    @Test(priority = 3, dataProvider = "roleInfo")
    public void addRole(Map<String, String> roleInfo) throws InterruptedException, IOException {
        //进入系统管理-操作员管理页面，显示租户列表
        pageList.getList(driver, "系统管理", "角色管理");
        roleService.addRole(driver, roleInfo);
        Thread.sleep(3000);
    }

    @Test(priority = 4, dependsOnMethods = "addRole")
    public void end() {
        browserService.quitDriver();
    }
}
