package com.newsdatapipeline.backend.Models;

import org.newspipeline.Article;
import java.util.Objects;

public class News implements Article {
    private String url;
    private String title;
    private String location;
    private String image;
    private String date;
    public News(String url, String title, String location, String date, String image) {
        this.url = url;
        this.title = title;
        this.location = location;
        this.date = date;
        this.image = image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
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

    @Override
    public String getTime() {
        return date;
    }

    @Override
    public String getImageUrl() {
        return image;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
