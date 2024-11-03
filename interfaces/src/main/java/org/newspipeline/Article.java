package org.newspipeline;

public interface Article {
    public String getUrl();
    public String getTitle();
    public String getLocation();
    public int hashCode();
    public boolean equals(Object o);
}
