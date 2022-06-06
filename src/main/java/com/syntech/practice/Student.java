/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author hrishi
 */
public class Student implements Serializable {

    @NotNull
    private String roll;
    @NotNull
    @Size(max = 5, min = 3)
//    @Email
    private String name;
    private String age;
    
    public Student(){
    }

    public Student(String roll, String name, String age) {
        this.roll = roll;
        this.name = name;
        this.age = age;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
