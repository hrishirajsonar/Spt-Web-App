/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class CountryController implements Serializable{
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private String name;
    private Country country;
    private List<Country> selectedCountries;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Country> getSelectedCountries() {
        return selectedCountries;
    }

    public void setSelectedCountries(List<Country> selectedCountries) {
        this.selectedCountries = selectedCountries;
    }

    private static final List<Country> countryList = new ArrayList<Country>(Arrays.asList(
            new Country(1, "Nepal"),
            new Country(2, "India"),
            new Country(3, "France"),
            new Country(4, "Russia"),
            new Country(5, "USA"),
            new Country(6, "Hungary")));
    
    public List<Country> getCountries(){
        return countryList;
    }

}
