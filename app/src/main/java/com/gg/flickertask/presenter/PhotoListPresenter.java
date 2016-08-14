package com.gg.flickertask.presenter;

import com.gg.flickertask.model.Photo;

/**
 * Created by Gilad on 8/14/2016.
 */
public interface PhotoListPresenter {

    void onCreate();

    void userSearchPhotos(String text);

    void userSelectedPhoto(Photo photo);

    void getNextSearchPage(String text, int page);
}
