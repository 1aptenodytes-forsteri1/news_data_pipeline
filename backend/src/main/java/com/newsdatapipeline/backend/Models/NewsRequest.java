package com.newsdatapipeline.backend.Models;


public class NewsRequest {
    private Settlement settlement;
    private DataAPI dataAPI;

    public NewsRequest(Settlement settlement, DataAPI dataAPI) {
        this.settlement = settlement;
        this.dataAPI = dataAPI;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public DataAPI getDataAPI() {
        return dataAPI;
    }

    public void setDataAPI(DataAPI dataAPI) {
        this.dataAPI = dataAPI;
    }


}
