package com.newsdatapipeline.backend.Models;

public class GetSettlementsRequest {
    private String lat;
    private String lng;
    private Integer radius;
    private Integer population;
    private Boolean showSmall;
    private Boolean showMedium;
    private Boolean showBig;

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Boolean getShowSmall() {
        return showSmall;
    }

    public void setShowSmall(Boolean showSmall) {
        this.showSmall = showSmall;
    }

    public Boolean getShowMedium() {
        return showMedium;
    }

    public void setShowMedium(Boolean showMedium) {
        this.showMedium = showMedium;
    }

    public Boolean getShowBig() {
        return showBig;
    }

    public void setShowBig(Boolean showBig) {
        this.showBig = showBig;
    }

}
