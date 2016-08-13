package com.gg.flickertask.model;

/**
 * Created by Gilad on 8/13/2016.
 */
public class Photo {
    public String id;
    public String owner;
    public String title;
    public String urlS;
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
     * @param urlS
     * @param owner
     */
    public Photo(String id, String owner, String title, String urlS) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.urlS = urlS;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", title='" + title + '\'' +
                ", urlS='" + urlS + '\'' +
                '}';
    }
}
