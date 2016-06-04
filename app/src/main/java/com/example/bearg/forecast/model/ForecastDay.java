package com.example.bearg.forecast.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bearg on 6/3/2016.
 */
public class ForecastDay {

    private int period;
    private String title;
    @SerializedName("fcttext")
    private String forecastText;
}
