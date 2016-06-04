package com.example.bearg.forecast.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bearg on 6/3/2016.
 */
public class ThreeDayForecast {

    @SerializedName("txt_forecast")
    private TextForecast textForecast;

    @SerializedName("simpleforecast")
    private SimpleForecast simpleForecast;

}
