package com.example.bearg.forecast.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bearg on 6/3/2016.
 */
public class TextForecast {

    private String date;
    @SerializedName("forecastday")
    private ForecastDay forecastDay;

}
