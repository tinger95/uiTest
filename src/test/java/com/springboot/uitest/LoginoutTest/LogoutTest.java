package com.springboot.uitest.LoginoutTest;

import com.springboot.remote.LoginService;
import com.springboot.remote.LoginServiceImpl;
import com.springboot.uitest.OpenTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {LoginServiceImpl.class})
public class LogoutTest extends AbstractTestNGSpringContextTests {
    @Autowired
    LoginService loginService;

    /**
     *  登出系统
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 1)
    public void logout() throws InterruptedException, IOException {
        loginService.logout(OpenTest.driver);
        Thread.sleep(3000);
    }

}
