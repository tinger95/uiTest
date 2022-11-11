package com.springboot.uitest.RoleTest;

import com.springboot.common.PageList;
import com.springboot.data.Browser;
import com.springboot.data.Lessee;
import com.springboot.data.Role;
import com.springboot.data.SystemAdmin;
import com.springboot.remote.*;
import com.springboot.uitest.OpenTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest(classes = {BrowserServiceImpl.class, Browser.class, LoginServiceImpl.class, RoleServiceImpl.class, Lessee.class, Role.class, SystemAdmin.class})
public class AddRoleTest extends AbstractTestNGSpringContextTests {
    @Autowired
    BrowserService browserService;
    @Autowired
    LoginService loginService;
    @Autowired
    RoleService roleService;
    @Autowired
    Lessee lessee;
    @Autowired
    Role role;
    @Autowired
    SystemAdmin systemAdmin;

    PageList pageList = new PageList();

    /**
     * 系统租户admin登录系统
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 2)
    public void loginSystem() throws InterruptedException, IOException {
        loginService.login(OpenTest.driver, systemAdmin.getLesseeName(), systemAdmin.getUserName(), systemAdmin.getPassWord());
        Thread.sleep(5000);
    }

    /**
     * 普通租户admin登录系统
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 3)
    public void loginNormal() throws InterruptedException, IOException {
        loginService.login(OpenTest.driver, lessee.getName(), "admin", lessee.getPassWord());
        Thread.sleep(5000);
    }

    /**
     * 新增角色
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(priority = 4)
    public void addRole() throws InterruptedException, IOException {
        //进入系统管理-操作员管理页面，显示角色列表
        pageList.getList(OpenTest.driver, "系统管理", "角色管理");
        roleService.addRole(OpenTest.driver, role);
        Thread.sleep(3000);
    }

}
