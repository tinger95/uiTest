package com.springboot.uitest.LoginoutTest;

import com.springboot.data.Lessee;
import com.springboot.data.Operator;
import com.springboot.data.SystemAdmin;
import com.springboot.remote.*;
import com.springboot.uitest.OpenTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {LoginServiceImpl.class, SystemAdmin.class, Lessee.class, Operator.class, OperatorServiceImpl.class})
public class LoginTest extends AbstractTestNGSpringContextTests {
    @Autowired
    LoginService loginService;
    @Autowired
    SystemAdmin systemAdmin;
    @Autowired
    Lessee lessee;
    @Autowired
    Operator operator;
    @Autowired
    OperatorService operatorService;
    /**
     * admin登录系统租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 1)
    public void adminLogin_System() throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, systemAdmin.getLesseeName(), systemAdmin.getUserName(), systemAdmin.getPassWord());
        Thread.sleep(5000);
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(OpenTest.driver, operator.getPassWord());
        Thread.sleep(3000);
    }

    /**
     * 租户管理员登录租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 2)
    public void adminLogin_Normal() throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, lessee.getName(), "admin", lessee.getPassWord());
        Thread.sleep(3000);
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(OpenTest.driver, operator.getPassWord());
        Thread.sleep(3000);
    }

    /**
     * 操作员登录系统租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 2)
    public void operatorLogin_System() throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, systemAdmin.getLesseeName(), operator.getLoginName(), operator.getPassWord());
        Thread.sleep(3000);
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(OpenTest.driver, operator.getPassWord());
        Thread.sleep(3000);
    }

    /**
     * 操作员登录普通租户
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 2)
    public void operatorLogin_Normal() throws InterruptedException, IOException {
        //登录系统
        loginService.login(OpenTest.driver, lessee.getName(), operator.getLoginName(), operator.getPassWord());
        Thread.sleep(3000);
        //首次使用初始密码登录，修改初始密码为1qaz2wsx
        operatorService.updatePassWord(OpenTest.driver, operator.getPassWord());
        Thread.sleep(3000);
    }
//
//    @Test(priority = 1)
//    @Parameters({"lesseeName","userName","passWord"})
//    public void login(String lesseeName,String userName,String passWord) throws InterruptedException, IOException {
//        //登录系统
//        loginService.login(OpenTest.driver, lesseeName, userName, passWord);
//        Thread.sleep(5000);
//    }

}