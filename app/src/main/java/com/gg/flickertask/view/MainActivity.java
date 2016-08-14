package com.gg.flickertask.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.gg.flickertask.R;
import com.gg.flickertask.adapters.PhotoAdapter;
import com.gg.flickertask.model.Photo;
import com.gg.flickertask.model.Photos;
import com.gg.flickertask.presenter.PhotoListPresenter;
import com.gg.flickertask.presenter.PhotoListPresenterImpl;
import com.gg.flickertask.util.DeviceServices;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gilad on 8/13/2016.
 */

/**
 * Main screen, shows photo search result in a list
 */
public class MainActivity extends AppCompatActivity implements PhotoListView {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String PHOTO_OBJECT_KEY = "PhotoObject";
    public static final int FIRST_PAGE = 1;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private PhotoAdapter mPhotoAdapter;
    private EditText mSearchEditText;
    private PhotoListPresenter mPhotoListPresenter;
    private String mSearchText;
    @BindView (R.id.status_txv)
    TextView mStatusTxv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSearchEditText = (EditText) findViewById(R.id.serachEditText);
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (DeviceServices.isNetworkConnected(MainActivity.this)) {
                    if (text != null && !text.equals("")) {
                        setTextStatus("Fetching data...");
                        mPhotoListPresenter.userSearchPhotos(text.toString());
                    } else {
                        mPhotoAdapter.setPhotos(null);
                    }
                } else {
                    setTextStatus(getString(R.string.no_connection));
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        setRecyclerView();
        mPhotoListPresenter = new PhotoListPresenterImpl(MainActivity.this);
        mPhotoListPresenter.onCreate();
    }

    private void setRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.photos_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPhotoAdapter = new PhotoAdapter(null, new PhotoAdapter.PhotoAdapterListener() {
            @Override
            public void onItemClick(Photo item) {
                mPhotoListPresenter.userSelectedPhoto(item);
            }

            @Override
            public void getNextSearchPage(int page) {
                String text = mSearchEditText.getText().toString();
                if (!text.equals("")) {
                    mPhotoListPresenter.getNextSearchPage(text, page);
                }
            }
        });
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPhotos(Photos photos) {
        Log.d(TAG, "setPhotos: " + photos);
        setTextStatus("");
        mPhotoAdapter.setPhotos(photos);
    }

    @Override
    public void addPhotos(Photos photos) {
        Log.d(TAG, "addPhotos: " + photos);
        setTextStatus("");
        mPhotoAdapter.addPhotos(photos);
    }

    @Override
    public void setTextStatus(String status) {
        Log.w(TAG, "setTextStatus: " + status);
        mStatusTxv.setText(status);
        mPhotoAdapter.setIsRequesting(false);

    }

    @Override
    public void setProgress(boolean inProgress) {
        Log.d(TAG, "setProgress: " + inProgress);

    }

    @Override
    public void startPhotoScreen(Photo photo) {
        Log.d(TAG, "startPhotoScreen");
        Intent startFullScreenPhotoIntent = new Intent(MainActivity.this, PhotoActivity.class);
        startFullScreenPhotoIntent.putExtra(PHOTO_OBJECT_KEY, photo);
        startActivity(startFullScreenPhotoIntent);
    }
}
