package com.example.bearg.forecast.interfaces;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bearg.forecast.model.currentconditions.CurrentConditions;
import com.example.bearg.forecast.model.currentconditions.CurrentObservation;
import com.example.bearg.forecast.model.threedayforecast.ThreeDayForecast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by bearg on 6/17/2016.
 */
public class WeatherApiManager {

    private static final String WEATHER_BASE_URL =
            "http://api.wunderground.com/api/3c39584cb3cf6c8f/";

    public interface WeatherService {

        @GET("conditions/q/{location}.json")
        Observable<CurrentConditions> getObservation(@Path("location") String location);

        @GET("forecast/q/{location}.json")
        Observable<ThreeDayForecast> getForecast(@Path("location") String location);

    }

    private static Gson gson = new GsonBuilder()
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(WEATHER_BASE_URL)
            .client(getClient())
            .build();

    private static final WeatherService WEATHER_SERVICE = retrofit.create(WeatherService.class);

    public static WeatherService getWeatherService() {
        return WEATHER_SERVICE;
    }

    @NonNull
    private static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("OkHttp: ", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return httpClient.build();
    }


}
