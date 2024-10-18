package com.newsdatapipeline.backend.Models;

import java.util.Objects;

public class Settlement {
    private String adminCode1;
    private String adminName1;
    private String name;
    private String lng;
    private String lat;
    private Integer population;
    private String fcodeName;

    public String getAdminCode1() {
        return adminCode1;
    }

    public void setAdminCode1(String adminCode1) {
        this.adminCode1 = adminCode1;
    }

    public String getAdminName1() {
        return adminName1;
    }

    public void setAdminName1(String adminName1) {
        this.adminName1 = adminName1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
    public String getFcodeName() {
        return fcodeName;
    }

    public void setFcodeName(String fcodeName) {
        this.fcodeName = fcodeName;
    }
}
