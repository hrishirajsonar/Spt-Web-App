/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import java.io.Serializable;
import java.util.Objects;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class Task implements Serializable{
    
    private int id;
    private String status;
    private int points;
    private String developer;
    private String requestor;
    private String reviewer;

    public Task() {
    }

    public Task(int id, String status, int points, String developer, String requestor, String reviewer) {
        this.id = id;
        this.status = status;
        this.points = points;
        this.developer = developer;
        this.requestor = requestor;
        this.reviewer = reviewer;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.status);
        hash = 71 * hash + this.points;
        hash = 71 * hash + Objects.hashCode(this.developer);
        hash = 71 * hash + Objects.hashCode(this.requestor);
        hash = 71 * hash + Objects.hashCode(this.reviewer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.points != other.points) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.developer, other.developer)) {
            return false;
        }
        if (!Objects.equals(this.requestor, other.requestor)) {
            return false;
        }
        if (!Objects.equals(this.reviewer, other.reviewer)) {
            return false;
        }
        return true;
    }
    
    
}
