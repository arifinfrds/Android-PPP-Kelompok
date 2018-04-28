package com.arifinfrds.ppp_kelompok.service;

import com.arifinfrds.ppp_kelompok.model.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by arifinfrds on 4/28/18.
 */

// Menurut https://www.raywenderlich.com/109843/common-design-patterns-for-android
// - Library Retrofit masuk Facade di Structural Pattern
public interface WeatherService {

    @GET("forecast?")
    Call<WeatherResponse> fetchWeather(@Query("appid") String appId, @Query("q") String cityNameAndCode);

}
