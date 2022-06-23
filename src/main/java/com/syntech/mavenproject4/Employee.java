/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.mavenproject4;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hrishi
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable{
    
    @Id
    @Column(name = "employeeId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    
    @Column(name = "employeeName", nullable = false)
    private String employeeName;
    
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "userpassword", nullable = false)
    private String userpassword;
    
    @Column(name = "employeeRole", nullable = false)
    private String employeeRole;
    
    public Employee() {
    }

    public Employee(String employeeName, String username, String userpassword, String employeeRole) {
        this.employeeName = employeeName;
        this.username = username;
        this.userpassword = userpassword;
        this.employeeRole = employeeRole;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

}
