package com.newsdatapipeline.backend.Config;

import com.newsdatapipeline.backend.Models.DataAPI;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties

public class ConfigFileTemplate {
    private String searchLocation;
    private String searchNearby;
    private List<DataAPI> newsAPIs;

    public List<DataAPI> getNewsAPIs() {
        return newsAPIs;
    }

    public void setNewsAPIs(List<DataAPI> newsAPIs) {
        this.newsAPIs = newsAPIs;
    }

    public String getSearchNearby() {
        return searchNearby;
    }

    public void setSearchNearby(String searchNearby) {
        this.searchNearby = searchNearby;
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public void setSearchLocation(String searchLocation) {
        this.searchLocation = searchLocation;
    }
}
