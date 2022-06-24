/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.bean;

import com.syntech.mavenproject4.Employee;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@SessionScoped
public class UserBean implements Serializable{
    
    private Employee user;

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
    
    public boolean isLoggedIn() {
        return user.getEmployeeId() != null;
    }
}
