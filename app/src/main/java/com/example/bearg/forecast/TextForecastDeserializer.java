package com.example.bearg.forecast;

import com.example.bearg.forecast.model.threedayforecast.Forecastday;
import com.example.bearg.forecast.model.threedayforecast.TxtForecast;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by bearg on 6/6/2016.
 */
public class TextForecastDeserializer implements JsonDeserializer<Forecastday> {

    @Override
    public Forecastday deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {

        JsonElement forecastDayJson = json.getAsJsonObject()
                .get("forecastday");

        final JsonObject forecastAsJsonObject = forecastDayJson.getAsJsonObject();

        String dayOrNight = forecastAsJsonObject.get("title").getAsString();
        String forecastText = forecastAsJsonObject.get("fcttext").getAsString();

        final Forecastday forecastday = new Forecastday();
        forecastday.title = dayOrNight;
        forecastday.fcttext = forecastText;

        return new Gson().fromJson(forecastDayJson, Forecastday.class);

    }
}
