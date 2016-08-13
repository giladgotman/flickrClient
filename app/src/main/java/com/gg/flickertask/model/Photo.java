package com.gg.flickertask.model;

/**
 * Created by Gilad on 8/13/2016.
 */
public class Photo {
    public String id;
    public String owner;
    public String title;
    public String url_s;
    /**
     * No args constructor for use in serialization
     *
     */
    public Photo() {
    }

    /**
     *
     * @param id
     * @param title
     * @param url_s
     * @param owner
     */
    public Photo(String id, String owner, String title, String url_s) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.url_s = url_s;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", title='" + title + '\'' +
                ", urlS='" + url_s + '\'' +
                '}';
    }
}
