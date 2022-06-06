/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import java.io.Serializable;
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
public class GrowlView implements Serializable{
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public void showInfo(){
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "This is message info. Hello there!");
    }
    public void showWarn(){
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn msg","Warn info"));
    }
    public void showMultiple(){
        addMessage(FacesMessage.SEVERITY_INFO, "Message 1", "Message Content 1");
        addMessage(FacesMessage.SEVERITY_INFO, "Message 2", "Message Content 2");
        addMessage(FacesMessage.SEVERITY_INFO, "Message 3", "Message Content 3");
    }
}
