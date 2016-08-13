package com.gg.flickertask.model;

/**
 * Created by Gilad on 8/13/2016.
 */
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Photos {

    public String total;
    @SerializedName("photo")
    public List<Photo> mPhotoList = new ArrayList<Photo>();
    /**
     * No args constructor for use in serialization
     *
     */
    public Photos() {
    }

    /**
     *
     * @param total
     * @param photoList
     */
    public Photos(String total, List<Photo> photoList) {
        this.total = total;
        this.mPhotoList = photoList;
    }

    @Override
    public String toString() {
        return "Photos{" +
                "total='" + total + '\'' +
                ", mPhotoList=" + mPhotoList +
                '}';
    }

    public String getTotal() {
        return total;
    }

    public List<Photo> getPhotoList() {
        return mPhotoList;
    }
}
