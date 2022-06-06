/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import java.io.Serializable;
import java.util.ArrayList;
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
public class TestController implements Serializable {

    private List<Student> studentList;
    private Student std;

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }
    
    @PostConstruct
    public void init() {
        std = new Student();
    }

    public List<Student> getStudents() {
        studentList = new ArrayList<Student>();

//        studentList.add(new Student("abc", "Ram", "four"));
//        studentList.add(new Student("roll2","Girish","five"));
//        studentList.add(new Student("abc", "Ram", "four"));
//        studentList.add(new Student("roll2","Girish","five"));
        return studentList;
    }

    public void addToList() {
        studentList.add(std);
    }
}
