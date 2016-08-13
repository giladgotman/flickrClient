package com.gg.flickertask;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gg.flickertask.model.PhotoSearchResult;
import com.gg.flickertask.network.SearchPhotoApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String TAG = MainActivity.class.getSimpleName();
    private Retrofit mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupNetwork();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    simpleSearchCall("food333");
                }
            });
        }

    }

    private void setupNetwork() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        mApiService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void simpleSearchCall(String text) {
        SearchPhotoApi searchPhotoApi = mApiService.create(SearchPhotoApi.class);
        Call<PhotoSearchResult> call = searchPhotoApi.getPhotoSearchResult(text);
        call.enqueue(new Callback<PhotoSearchResult>() {
            @Override
            public void onResponse(Call<PhotoSearchResult> call, Response<PhotoSearchResult> response) {
                if (response != null) {
                    Log.d(TAG, "simpleSearchCall:onResponse: " + response.toString());
                    PhotoSearchResult res = response.body();
                    Log.d(TAG, "simpleSearchCall:res: " + res);
                }

            }

            @Override
            public void onFailure(Call<PhotoSearchResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());

            }
        });
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
