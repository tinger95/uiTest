package com.springboot.uitest.OperatorTest;

import com.springboot.common.PageList;
import com.springboot.data.Browser;
import com.springboot.data.Lessee;
import com.springboot.data.Operator;
import com.springboot.remote.*;
import com.springboot.uitest.OpenTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, LesseeServiceImpl.class, OperatorServiceImpl.class, Lessee.class, Operator.class})
public class AddOperatorTest extends AbstractTestNGSpringContextTests {
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

    /**
     * 登录系统
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 2)
    public void login() throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, lessee.getName(), "admin", lessee.getPassWord());
        Thread.sleep(5000);
    }

    /**
     * 新增操作员
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 3)
    public void addOperator() throws InterruptedException, IOException {
        //进入系统管理-操作员管理页面，显示操作员列表
        pageList.getList(OpenTest.driver, "系统管理", "操作员管理");

        //新增操作员，密码为返回值
        tempPWD = operatorService.addOperator(OpenTest.driver, operator);
        System.out.println(tempPWD);
        Thread.sleep(3000);
    }

    /**
     * 登出系统
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4, dependsOnMethods = "addOperator")
    public void logout() throws InterruptedException, IOException {
        //登出系统
        loginService.logout(OpenTest.driver);
        Thread.sleep(2000);
    }

    /**
     * 普通租户新增操作员首次使用初始密码登录，修改初始密码为1qaz2wsx
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 5, dependsOnMethods = "logout")
    public void operatorLogin_Normal() throws InterruptedException, IOException {
        loginService.login(OpenTest.driver, lessee.getName(), operator.getLoginName(), tempPWD);
        Thread.sleep(3000);
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(OpenTest.driver, operator.getPassWord());
        Thread.sleep(3000);
    }

    /**
     * 系统租户新增操作员首次使用初始密码登录，修改初始密码为1qaz2wsx
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 6, dependsOnMethods = "logout")
    public void operatorLogin_System() throws InterruptedException, IOException {
        loginService.login(OpenTest.driver, "系统租户", operator.getLoginName(), tempPWD);
        Thread.sleep(3000);
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(OpenTest.driver, operator.getPassWord());
        Thread.sleep(3000);
    }
}