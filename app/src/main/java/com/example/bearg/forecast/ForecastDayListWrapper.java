package com.example.bearg.forecast;

import com.example.bearg.forecast.model.threedayforecast.Forecastday;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bearg on 6/6/2016.
 */
public class ForecastDayListWrapper {
    private List<Forecastday> textForecasts = new ArrayList<>();

    public List<Forecastday> getTextForecasts() {
        return textForecasts;
    }
}
