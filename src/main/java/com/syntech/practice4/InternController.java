/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class InternController implements Serializable {

    private String name;
    private String rollNo;
    private int age;

    private static final List<Intern> internList = new ArrayList<Intern>(Arrays.asList(
            new Intern("Ram", "12", 12),
            new Intern("Ramesh", "11", 11),
            new Intern("Shyam", "122", 432)));

    public List<Intern> getInterns() {
        return internList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String addIntern() {
        internList.add(new Intern(name, rollNo, age));
        return null;
    }

}
