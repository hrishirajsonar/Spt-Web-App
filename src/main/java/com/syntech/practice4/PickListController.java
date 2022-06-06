/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice4;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author hrishi
 */
@Named
//@ApplicationScoped
@ViewScoped
public class PickListController implements Serializable {

    private DualListModel<String> cities;

//    private List<String> citiesSource;
//    private List<String> citiesTarget;
    private List<String> addedSource;
    private List<String> addedSourceInEvent;
    private List<String> diffSource;

    private List<String> transaction;

    private List<String> addedTarget;
    private List<String> addedTargetInEvent;
    private List<String> diffTarget;

    public List<String> getAddedSourceInEvent() {
        return addedSourceInEvent;
    }

    public void setAddedSourceInEvent(List<String> addedSourceInEvent) {
        this.addedSourceInEvent = addedSourceInEvent;
    }

    public List<String> getDiffSource() {
        return diffSource;
    }

    public void setDiffSource(List<String> diffSource) {
        this.diffSource = diffSource;
    }

    public List<String> getAddedTargetInEvent() {
        return addedTargetInEvent;
    }

    public void setAddedTargetInEvent(List<String> addedTargetInEvent) {
        this.addedTargetInEvent = addedTargetInEvent;
    }

    public List<String> getDiffTarget() {
        return diffTarget;
    }

    public void setDiffTarget(List<String> diffTarget) {
        this.diffTarget = diffTarget;
    }

    public List<String> getAddedSource() {
        return addedSource;
    }

    public void setAddedSource(List<String> addedSource) {
        this.addedSource = addedSource;
    }

    public List<String> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<String> transaction) {
        this.transaction = transaction;
    }

    public List<String> getAddedTarget() {
        return addedTarget;
    }

    public void setAddedTarget(List<String> addedTarget) {
        this.addedTarget = addedTarget;
    }

    @PostConstruct
    public void nameItAnything() {

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
        addedSource = new ArrayList<>();
        addedSourceInEvent = new ArrayList<>();
        diffSource = new ArrayList<>();

        transaction = new ArrayList<>();

        addedTarget = new ArrayList<>();
        addedTargetInEvent = new ArrayList<>();
        diffTarget = new ArrayList<>();

    }

    public DualListModel<String> getCities() {
        return cities;
    }

    public void setCities(DualListModel<String> cities) {
        this.cities = cities;
    }

//    public void display() {
//        System.out.println("Source" + cities.getSource().size());
//        System.out.println("Target" + cities.getTarget().size());
//    }
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append((String) item).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void OnTransfer2(TransferEvent event) {
        LocalDateTime time = LocalDateTime.now();
        for (Object item : event.getItems()) {
            String t = time.toString() + " " + (String) item + " " + (event.isAdd() ? " (S -> T)" : " (T -> S)");
            transaction.add(t);
        }

        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {

            if (event.isAdd()) {

                String str = (String) item;

                addedTarget.add(str);
                addedTargetInEvent.add(str);

                addedSource.remove((String) item);
                addedSourceInEvent.remove((String) item);
            }
            if (event.isRemove()) {

                String str = (String) item;

                addedSource.add(str);
                addedSourceInEvent.add(str);

                addedTarget.remove((String) item);
                addedTargetInEvent.remove((String) item);
            }
        }
    }

    public void differenceInTarget() {

        addedTargetInEvent.retainAll(addedTarget);
        diffTarget.removeAll(diffTarget);

        diffTarget.addAll(addedTargetInEvent);

        addedTargetInEvent.removeAll(addedTargetInEvent);
        
         if(cities.getTarget().containsAll(diffTarget)){
            diffTarget.removeAll(diffTarget);
        }

    }

    public void differenceInSource() {

        addedSourceInEvent.retainAll(addedSource);
        diffSource.removeAll(diffSource);
        
        diffSource.addAll(addedSourceInEvent);
        
        addedSourceInEvent.removeAll(addedSourceInEvent);

        if(cities.getSource().containsAll(diffSource)){
            diffSource.removeAll(diffSource);
        }
    }

    public void difference() {

        differenceInSource();
        differenceInTarget();

    }

}
