package com.arifinfrds.ppp_kelompok.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arifinfrds.ppp_kelompok.R;
import com.arifinfrds.ppp_kelompok.model.pojo.Weather;
import com.arifinfrds.ppp_kelompok.model.pojo.WeatherList;
import com.arifinfrds.ppp_kelompok.model.response.WeatherResponse;
import com.arifinfrds.ppp_kelompok.service.RestClient;
import com.arifinfrds.ppp_kelompok.util.SpacesItemDecoration;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arifinfrds.ppp_kelompok.util.Constant.TAG_WEATHER;

public class WeatherActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;
    private ArrayList<WeatherList> mWeathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = findViewById(R.id.progressBar);
        mWeathers = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);

        fetchWeather();
    }

    // Menurut https://www.raywenderlich.com/109843/common-design-patterns-for-android
    // - getWeatherServiceInstance() masuk singleton di Creational Pattern
    private void fetchWeather() {
        mProgressBar.setVisibility(View.VISIBLE);
        RestClient.getWeatherServiceInstance().fetchWeather(RestClient.APP_ID, "Malang,ID").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                Log.d(TAG_WEATHER, "onResposne: isSuccessful: " + response.isSuccessful());
                Log.d(TAG_WEATHER, "onResposne: message: " + response.message());
                Toast.makeText(WeatherActivity.this, "onResponse: " + response.message(), Toast.LENGTH_SHORT).show();

                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    mWeathers = (ArrayList<WeatherList>) weatherResponse.getList();
                    setupRecyclerView();
                } else {
                    Toast.makeText(WeatherActivity.this, "onResponse: " + response.message(), Toast.LENGTH_SHORT).show();
                }
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG_WEATHER, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(WeatherActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );

        mAdapter = new WeatherAdapter(mWeathers);
        mRecyclerView.setAdapter(mAdapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space_between_card);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }
}
