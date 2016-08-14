package com.gg.flickertask.model;

/**
 * Created by Gilad on 8/13/2016.
 */
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Photos object holding list of photos and paging information
 */
public class Photos {

    @SerializedName("total")
    private String mTotalItems;
    @SerializedName("pages")
    private String mPages;
    @SerializedName("perpage")
    private String mItemsPerPage;
    @SerializedName("photo")
    private List<Photo> mPhotoList = new ArrayList<Photo>();
    /**
     * No args constructor for use in serialization
     *
     */
    public Photos() {
    }

    public String getTotalItems() {
        return mTotalItems;
    }

    public List<Photo> getPhotoList() {
        return mPhotoList;
    }

    public String getPages() {
        return mPages;
    }

    public String getItemsPerPage() {
        return mItemsPerPage;
    }
    public int getPerpageInt() {
        int pageInt = 1;
        if (mItemsPerPage != null) {
            pageInt = Integer.parseInt(mItemsPerPage);
        }
        return pageInt;
    }

    public int getPageseInt() {
        int pagesInt = 1;
        if (mPages != null) {
            pagesInt = Integer.parseInt(mPages);
        }
        return pagesInt;
    }


    @Override
    public String toString() {
        return "Photos{" +
                "mTotalItems='" + mTotalItems + '\'' +
                ", mPages='" + mPages + '\'' +
                ", mItemsPerPage='" + mItemsPerPage + '\'' +
                ", mPhotoList=" + mPhotoList +
                '}';
    }
}
