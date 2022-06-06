/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@ApplicationScoped
public class TaskView implements Serializable{
    
    private List<Task> tasks;
    
    @Inject
    private TaskService service;
    
    @PostConstruct
    public void init(){
        tasks = service.getTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setService(TaskService service) {
        this.service = service;
    }
}
