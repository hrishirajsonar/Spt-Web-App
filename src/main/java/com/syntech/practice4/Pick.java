/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class Pick implements Serializable {

    private DualListModel<String> cities;

    private List<String> initialSource;
    private List<String> lastSource;
    
    private List<String> initialTarget;
    private List<String> lastTarget;


    @PostConstruct
    public void init() {

        List<String> citiesSource = new ArrayList<>();
        List<String> citiesTarget = new ArrayList<>();
        citiesTarget.add("Kathmandu");
        citiesTarget.add("Banepa");
        citiesTarget.add("Butwal");
        citiesTarget.add("Pokhara");
        citiesSource.add("Mumbai");
        citiesSource.add("Beijing");
        citiesSource.add("London");
        citiesSource.add("Chicago");
        citiesSource.add("Cairo");
        citiesSource.add("Abu Dhabi");

        cities = new DualListModel<>(citiesSource, citiesTarget);

        initialSource = new ArrayList<>();
        lastSource = new ArrayList<>(citiesSource);
        initialTarget = new ArrayList<>();
        lastTarget = new ArrayList<>(citiesTarget);
    }

    public void swap() {
        
        initialSource.removeAll(initialSource);
        initialSource.addAll(lastSource);
        initialSource.removeAll(cities.getSource());
        
        lastSource.removeAll(lastSource);
        lastSource.addAll(cities.getSource());
        
        
        
        initialTarget.removeAll(initialTarget);
        initialTarget.addAll(lastTarget);
        initialTarget.removeAll(cities.getTarget());
        
        lastTarget.removeAll(lastTarget);
        lastTarget.addAll(cities.getTarget());
    }
    
    

    public DualListModel<String> getCities() {
        return cities;
    }

    public void setCities(DualListModel<String> cities) {
        this.cities = cities;
    }

    public List<String> getInitialSource() {
        return initialSource;
    }

    public void setInitialSource(List<String> initialSource) {
        this.initialSource = initialSource;
    }

    public List<String> getLastSource() {
        return lastSource;
    }

    public void setLastSource(List<String> lastSource) {
        this.lastSource = lastSource;
    }
    
    public List<String> getInitialTarget() {
        return initialTarget;
    }

    public void setInitialTarget(List<String> initialTarget) {
        this.initialTarget = initialTarget;
    }

    public List<String> getLastTarget() {
        return lastTarget;
    }

    public void setLastTarget(List<String> lastTarget) {
        this.lastTarget = lastTarget;
    }

}
