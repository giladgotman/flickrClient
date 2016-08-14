package com.gg.flickertask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.gg.flickertask.R;
import com.gg.flickertask.model.Photo;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;

/**
 * Full screen photo with inormation
 */
public class PhotoActivity extends AppCompatActivity {
    
    private static final String TAG = PhotoActivity.class.getSimpleName();
    ImageView mFullScreenPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFullScreenPhoto = (ImageView) findViewById(R.id.fullscreenPhoto);
        Intent photoIntent = getIntent();
        if (photoIntent != null) {
            Photo photo = (Photo) photoIntent.getSerializableExtra(MainActivity.PHOTO_OBJECT_KEY);
            String photoUrl = photo.getUrlOriginalSize();
            Log.d(TAG, "onCreate: photo title:" + photo.getTitle());
            if (photoUrl != null) {
                Picasso.with(PhotoActivity.this)
                        .load(photoUrl)
                        .into(mFullScreenPhoto);
            }
        }
    }
}
