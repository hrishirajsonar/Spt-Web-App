/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class AjaxView implements Serializable{
    
    private int num1;
    private String firstName;
    private String middleName;
    private String lastName;
    private String name2;
    private String city;
    
    private String mentor;
    private String intern;
    private Map<String, String> mentors;
    private Map<String, String> interns;
    private Map<String, Map<String, String>> data = new HashMap<>();
    
    @PostConstruct
    public void init(){
        mentors = new HashMap<>();
        mentors.put("Sanjiv", "Sanjiv");
        mentors.put("Prakash", "Prakash");
        
        Map<String, String> map = new HashMap<>();
        map.put("Bipan", "Bipan");
        map.put("Rashmi", "Rashmi");
        map.put("Prakash2", "Prakash2");
        data.put("Sanjiv", map);
        
        map = new HashMap<>();
        map.put("Hrishi", "Hrishi");
        map.put("Santosh", "Santosh");
        map.put("Madan", "Madan");
        data.put("Prakash", map);
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }
    
    public void square(){
        num1 = num1*num1;
    }
    
    public void cube(){
        num1 = num1*num1*num1;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
    
    public void handleKeyEvent(){
        name2 = name2.toUpperCase();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public void showInfo(){
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", city);
    }

    public String getMentor() {
        return mentor;
    }

    public String getIntern() {
        return intern;
    }

    public Map<String, String> getMentors() {
        return mentors;
    }

    public Map<String, String> getInterns() {
        return interns;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public void setIntern(String intern) {
        this.intern = intern;
    }
    
    public void onMentorChange(){
        if (mentor != null && !"".equals(mentor)) {
            interns = data.get(mentor);
        }
        else {
            interns = new HashMap<>();
        }
    }
    
    public void displayIntern() {
        FacesMessage msg;
        if (intern != null && mentor != null) {
            msg = new FacesMessage("Selected", city + " of " + mentor);
        }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Intern is not selected.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
