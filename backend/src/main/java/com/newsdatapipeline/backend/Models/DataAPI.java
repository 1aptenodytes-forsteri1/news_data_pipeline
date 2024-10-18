package com.newsdatapipeline.backend.Models;

public class DataAPI {
    private String link;
    private String responseListName;
    private String responseTitle;
    private String responseLink;

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
