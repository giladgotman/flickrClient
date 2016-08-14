package com.gg.flickertask.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.gg.flickertask.R;
import com.gg.flickertask.adapters.PhotoAdapter;
import com.gg.flickertask.model.Photo;
import com.gg.flickertask.model.PhotoSearchResult;
import com.gg.flickertask.network.NetworkHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String PHOTO_OBJECT_KEY = "PhotoObject";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private PhotoAdapter mPhotoAdapter;
    EditText mSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSearchEditText = (EditText) findViewById(R.id.serachEditText);
        setRecyclerView();

        setupNetwork();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String searchText = mSearchEditText.getText().toString();
                    if (searchText != null && !searchText.equals("")) {
                        sendGetPhotoSearchResult(searchText);
                    } else {
                        mSearchEditText.setError("Please enter text");
                    }
                }
            });
        }
    }

    private void setRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.photos_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPhotoAdapter = new PhotoAdapter(null, new PhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo item) {
                Intent startFullScreenPhotoIntent = new Intent(MainActivity.this, PhotoActivity.class);
                startFullScreenPhotoIntent.putExtra(PHOTO_OBJECT_KEY, item);
                startActivity(startFullScreenPhotoIntent);

            }
        });
        mRecyclerView.setAdapter(mPhotoAdapter);

    }

    private void setupNetwork() {
            NetworkHelper.getInstance().setup();
    }

    private void sendGetPhotoSearchResult(String text) {
            NetworkHelper.getInstance().getPhotoSearchResult(text, new Callback<PhotoSearchResult>() {
                @Override
                public void onResponse(Call<PhotoSearchResult> call, Response<PhotoSearchResult> response) {
                    if (response != null) {
                        Log.d(TAG, "sendGetPhotoSearchResult:onResponse: " + response.toString());
                        PhotoSearchResult res = response.body();
                        Log.d(TAG, "sendGetPhotoSearchResult:res: " + res);
                        mPhotoAdapter.setPhotos(res.photos);
                        mPhotoAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<PhotoSearchResult> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.toString());
                }
            },1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
