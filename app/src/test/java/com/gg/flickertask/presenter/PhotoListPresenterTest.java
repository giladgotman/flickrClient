package com.gg.flickertask.presenter;

import com.gg.flickertask.model.Photo;
import com.gg.flickertask.network.NetworkHelper;
import com.gg.flickertask.view.PhotoListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by Gilad on 8/14/2016.
 */
public class PhotoListPresenterTest {

    PhotoListPresenter mPhotoListPresenter;

    @Mock
    PhotoListView mPhotoListView;
    @Mock
    NetworkHelper mNetworkHelper;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userSelectedPhotoTest() {
        mPhotoListPresenter = new PhotoListPresenterImpl(mPhotoListView);
        Photo photo = new Photo();
        photo.setTitle("testPhoto");
        mPhotoListPresenter.userSelectedPhoto(photo);
        verify(mPhotoListView).startPhotoScreen(photo);

    }

    @Test(expected= IllegalArgumentException.class)
    public void userSearchPhotosTest() {
        mPhotoListPresenter = new PhotoListPresenterImpl(mPhotoListView);
        mPhotoListPresenter.userSearchPhotos(null);
    }
    @Test
    public void onCreateTest() {
        mPhotoListPresenter = new PhotoListPresenterImpl(mPhotoListView);
        mPhotoListPresenter.onCreate();
        verify(mNetworkHelper).getInstance().setup();
    }

}
