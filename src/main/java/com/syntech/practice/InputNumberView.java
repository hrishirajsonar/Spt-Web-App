/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */

@Named
@ViewScoped
public class InputNumberView implements Serializable{
    
    private Double input1;
    
    private String result;
    
    public void submit() {
        result = "Hello Idiot";
//        System.out.println(result);
    }

    public Double getInput1() {
        return input1;
    }

    public void setInput1(Double input1) {
        this.input1 = input1;
    }
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
