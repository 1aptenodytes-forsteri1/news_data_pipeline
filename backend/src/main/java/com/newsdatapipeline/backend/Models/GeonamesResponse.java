package com.newsdatapipeline.backend.Models;

import java.util.List;

public class GeonamesResponse {
    private Integer totalResultsCount;
    private List<Settlement> geonames;

    public Integer getTotalResultsCount() {
        return totalResultsCount;
    }

    public void setTotalResultsCount(Integer totalResultsCount) {
        this.totalResultsCount = totalResultsCount;
    }

    public List<Settlement> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<Settlement> geonames) {
        this.geonames = geonames;
    }

}
