package com.gg.flickertask.model;

import java.io.Serializable;

/**
 * Created by Gilad on 8/13/2016.
 */
public class Photo implements Serializable{
    public String id;
    public String owner;
    public String title;
    public String url_s;
    public String url_o;
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
    public Photo(String id, String owner, String title, String url_s, String url_o) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.url_s = url_s;
        this.url_o = url_o;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", title='" + title + '\'' +
                ", url_s='" + url_s + '\'' +
                ", url_o='" + url_o + '\'' +
                '}';
    }
}
