package org.newspipeline;

public interface Article {
    public String getUrl();
    public String getTitle();
    public String getLocation();
    public String getTime();
    public String getImageUrl();
    public int hashCode();
    public boolean equals(Object o);
}
