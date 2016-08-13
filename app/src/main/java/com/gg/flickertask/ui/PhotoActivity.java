package com.gg.flickertask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.gg.flickertask.R;
import com.gg.flickertask.model.Photo;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;

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
            String photoUrl = photo.url_o;
            Log.d(TAG, "onCreate: photo title:" + photo.title);
            if (photoUrl != null) {
                Picasso.with(PhotoActivity.this)
                        .load(photoUrl)
                        .into(mFullScreenPhoto);
            }

        }
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    
}
