/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice4;

/**
 *
 * @author hrishi
 */
public class Student {

    private String name;
    private String stuClass;
    private String rollNo;
    private int age;
    private boolean editable;

    public Student() {
    }

    public Student(String name, String stuClass, String rollNo, int age) {
        this.name = name;
        this.stuClass = stuClass;
        this.rollNo = rollNo;
        this.age = age;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
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

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
}
