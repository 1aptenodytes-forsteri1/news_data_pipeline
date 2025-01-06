package com.newsdatapipeline.backend.Models;

public class DataAPI {
    private String link;
    private String responseListName;
    private String responseTitle;
    private String responseLink;
    private String responseImageUrl;
    private String responseDate;
    private String dateFormat;
    public String getResponseImageUrl() {
        return responseImageUrl;
    }

    public void setResponseImageUrl(String responseImageUrl) {
        this.responseImageUrl = responseImageUrl;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getResponseListName() {
        return responseListName;
    }

    public void setResponseListName(String responseListName) {
        this.responseListName = responseListName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getResponseTitle() {
        return responseTitle;
    }

    public void setResponseTitle(String responseTitle) {
        this.responseTitle = responseTitle;
    }

    public String getResponseLink() {
        return responseLink;
    }

    public void setResponseLink(String responseLink) {
        this.responseLink = responseLink;
    }


}
