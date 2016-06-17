package com.example.bearg.forecast;

import com.example.bearg.forecast.model.threedayforecast.Forecastday;
import com.example.bearg.forecast.model.threedayforecast.TxtForecast;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bearg on 6/6/2016.
 */
public class TextForecastDeserializer implements JsonDeserializer<List<Forecastday>> {

    @Override
    public List<Forecastday> deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {

        JsonArray data = json.getAsJsonObject().getAsJsonArray("forecastday");
        List<Forecastday> forecastList = new ArrayList<>();

        for (JsonElement e : data) {
            forecastList.add((Forecastday) context.deserialize(e, Forecastday.class));
        }

       return forecastList;


    }
}
