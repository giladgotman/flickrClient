package com.gg.flickertask.presenter;

import android.util.Log;

import com.gg.flickertask.model.Photo;
import com.gg.flickertask.model.PhotoSearchResult;
import com.gg.flickertask.network.NetworkHelper;
import com.gg.flickertask.view.PhotoListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gilad on 8/14/2016.
 */
public class PhotoListPresenterImpl implements PhotoListPresenter{
    private static final String TAG = PhotoListPresenterImpl.class.getSimpleName();
    private static final int FIRST_PAGE = 1;
    PhotoListView mPhotoListView;

    public PhotoListPresenterImpl(PhotoListView mPhotoListView) {
        this.mPhotoListView = mPhotoListView;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        NetworkHelper.getInstance().setup();
    }

    @Override
    public void userSearchPhotos(final String text) {
        sendSearchPhotosRequest(text, FIRST_PAGE);
    }

    @Override
    public void userSelectedPhoto(Photo photo) {
        mPhotoListView.startPhotoScreen(photo);
    }


    //TODO duplicate code, make a function or something
    @Override
    public void getNextSearchPage(String text, int page) {
        mPhotoListView.setTextStatus("Getting more results...");
        sendSearchPhotosRequest(text, page);
    }

    public void sendSearchPhotosRequest(String text, final int page) {
        NetworkHelper.getInstance().getPhotoSearchResult(text, new Callback<PhotoSearchResult>() {
            @Override
            public void onResponse(Call<PhotoSearchResult> call, Response<PhotoSearchResult> response) {
                if (response != null) {
                    Log.d(TAG, "getNextSearchPage:onResponse: " + response.toString());
                    PhotoSearchResult res = response.body();
                    if (page == FIRST_PAGE) {
                        mPhotoListView.setPhotos(res.getPhotos());
                    } else {
                        mPhotoListView.addPhotos(res.getPhotos());
                    }

                }
            }

            @Override
            public void onFailure(Call<PhotoSearchResult> call, Throwable t) {
                Log.d(TAG, "getNextSearchPage:onFailure: " + t.toString());
                // TODO: set string in strings
                mPhotoListView.setTextStatus("Network Error");
            }
        }, page);
    }
}
