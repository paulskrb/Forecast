package com.example.bearg.forecast.interfaces;

import com.example.bearg.forecast.model.currentconditions.CurrentObservation;
import com.example.bearg.forecast.model.threedayforecast.Forecast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bearg on 6/4/2016.
 */
public interface WeatherService {


    @GET("conditions/q/{location}.json")
    Call<CurrentObservation> getObservation(@Path("location") String location);

    @GET("getForecast/q/{location}.json")
    Call<Forecast> getForecast(@Path("location") String location);



}
