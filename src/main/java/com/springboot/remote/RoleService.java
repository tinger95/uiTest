package com.springboot.remote;

import com.springboot.data.Role;
import org.openqa.selenium.WebDriver;

public interface RoleService {
    public void addRole(WebDriver driver, Role role) throws InterruptedException;
}
