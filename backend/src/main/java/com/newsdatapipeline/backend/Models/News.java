package com.newsdatapipeline.backend.Models;

import java.time.LocalDateTime;
import java.util.Objects;

public class News {
    private String url;
    private String title;
    private String location;

    public News(String url, String title, String location) {
        this.url = url;
        this.title = title;
        this.location = location;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
