/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice4;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class DialogBox implements Serializable{
    
    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", " Hello there good evening!");

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
}
