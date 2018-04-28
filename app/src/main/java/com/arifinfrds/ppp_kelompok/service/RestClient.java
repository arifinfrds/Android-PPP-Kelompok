package com.arifinfrds.ppp_kelompok.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arifinfrds on 4/28/18.
 */

// Menurut https://www.raywenderlich.com/109843/common-design-patterns-for-android
// - getWeatherServiceInstance() masuk singleton di Creational Pattern
// - Library Retrofit masuk Facade di Structural Pattern
public class RestClient {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String APP_ID = "3805dd8eda02a9e61920a575cd81b269";

    private static Retrofit retrofit;
    private static WeatherService weatherService;

    public static void initialize() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Singleton disini
    public static WeatherService getWeatherServiceInstance() {
        if (weatherService == null) {
            weatherService = retrofit.create(WeatherService.class);
            return weatherService;
        }
        return weatherService;
    }


}
