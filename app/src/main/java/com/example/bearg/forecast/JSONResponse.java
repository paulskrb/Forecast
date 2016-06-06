package com.example.bearg.forecast;

import com.example.bearg.forecast.model.threedayforecast.Forecastday;

/**
 * Created by bearg on 6/6/2016.
 */
public class JSONResponse {
    private Forecastday[] textForecasts;

    public Forecastday[] getTextForecasts() {
        return textForecasts;
    }
}
