package com.gg.flickertask.model;

/**
 * Created by Gilad on 8/13/2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Photos {

    public String total;
    public List<Photo> photo = new ArrayList<Photo>();
    /**
     * No args constructor for use in serialization
     *
     */
    public Photos() {
    }

    /**
     *
     * @param total
     * @param photo
     */
    public Photos(String total, List<Photo> photo) {
        this.total = total;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Photos{" +
                "total='" + total + '\'' +
                ", photo=" + photo +
                '}';
    }
}
