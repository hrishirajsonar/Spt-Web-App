/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@ApplicationScoped
public class TaskService {
    
    private List<Task> tasks;
    
    @PostConstruct
    public void init(){
        tasks = new ArrayList<>();
        tasks.add(new Task(1, "Started", 2, "Ram", "Shyam", "Rima"));
        tasks.add(new Task(2, "Finished", 4, "Dina", "Hari", "Milan"));
        
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
}
