package com.example.bearg.forecast.interfaces;

import com.example.bearg.forecast.ForecastDayListWrapper;
import com.example.bearg.forecast.model.currentconditions.CurrentObservation;
import com.example.bearg.forecast.model.threedayforecast.Forecastday;
import com.example.bearg.forecast.model.threedayforecast.ThreeDayForecast;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by bearg on 6/4/2016.
 */
public interface WeatherService {


    @GET("conditions/q/{location}.json")
    Observable<CurrentObservation> getObservation(@Path("location") String location);

    @GET("forecast/q/{location}.json")
    Observable<ThreeDayForecast> getForecast(@Path("location") String location);

}
