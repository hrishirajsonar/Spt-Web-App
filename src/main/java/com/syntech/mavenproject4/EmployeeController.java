/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.mavenproject4;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.syntech.bean.UserBean;
import com.syntech.util.MessageUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hrishi
 */
@Named
@ApplicationScoped
public class EmployeeController implements Serializable {

    private Employee loggedInUser;
    
    private String hideNoSelectOption;

    public String getHideNoSelectOption() {
        return hideNoSelectOption;
    }

    public void setHideNoSelectOption(String hideNoSelectOption) {
        this.hideNoSelectOption = hideNoSelectOption;
    }

    public Employee getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Employee loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public EmployeeRepository getEr() {
        return er;
    }

    public void setEr(EmployeeRepository er) {
        this.er = er;
    }

    public MessageUtil getMessageUtil() {
        return messageUtil;
    }

    public void setMessageUtil(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }
    
    private Employee employee;
    
    private List<Employee> employeeList;

    @Inject
    private EmployeeRepository er;

    @Inject
    private MessageUtil messageUtil;
    
    @Inject
    private UserBean userBean;

    @PostConstruct
    public void init() {
        employee = new Employee();
        loggedInUser = new Employee();
        employeeList = new ArrayList<>(er.findAll());
        
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void persistEmployee() {
        employee.setUserpassword(hashString(employee.getUserpassword()));
        er.persistEmployee(employee);
//        messageUtil.showInfo("User Created Successfully!");
        employee = new Employee();
    }

    public String hashString(String unHashedPassword) {
        return BCrypt.withDefaults().hashToString(12, unHashedPassword.toCharArray());
    }

    public String validateLogin() {

        Employee e = er.findByUserName(employee.getUsername());
        String savedPassword = e.getUserpassword();
        
        System.out.println(e.getUserpassword());
        System.out.println("hello " + savedPassword);
        System.out.println(employee.getUserpassword());
        System.out.println("password" + hashString(employee.getUserpassword()));

        if (isHashingMatched(employee.getUserpassword(), savedPassword)) {
//            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//            
//            Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
//            appMap.put(e.getEmployeeId().toString(), session);
//            
//            userBean.setUser(e);
            
            setLoggedInUser(e);
            return "/faces/mavenproject4/dashboard.xhtml?faces-redirect=true";
        }
        employee = new Employee();
        messageUtil.showInfo("Wrong password!");
        return "/faces/mavenproject4/login.xhtml?faces-redirect=true";
        
    }

    public boolean isHashingMatched(String password, String hashString) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashString);
        return result.verified;
    }
    
    public String logout() {
//        employee = new Employee();
//        loggedInUser = new Employee();
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/mavenproject4/login.xhtml?faces-redirect=true";
    }

}
