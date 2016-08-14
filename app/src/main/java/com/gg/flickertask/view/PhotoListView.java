package com.gg.flickertask.view;

import com.gg.flickertask.model.Photo;
import com.gg.flickertask.model.Photos;

/**
 * Created by Gilad on 8/14/2016.
 */
public interface PhotoListView {

    void setPhotos(Photos photos);

    void addPhotos(Photos photos);

    void onNetworkError(String errorText);

    void setProgress(boolean inProgress);

    void startPhotoScreen(Photo photo);
}
