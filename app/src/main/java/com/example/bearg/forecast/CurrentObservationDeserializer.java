package com.example.bearg.forecast;

import com.example.bearg.forecast.model.currentconditions.CurrentObservation;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by bearg on 6/4/2016.
 */
public class CurrentObservationDeserializer implements
        JsonDeserializer<CurrentObservation> {

    @Override
    public CurrentObservation deserialize(JsonElement json, Type typeOfT,
                                          JsonDeserializationContext context)
            throws JsonParseException {

        JsonElement observation = json.getAsJsonObject()
                .get("current_observation");

        final JsonObject observationAsJsonObject = observation.getAsJsonObject();

        String observationTime = observationAsJsonObject.get("observation_time").getAsString();
        String weather = observationAsJsonObject.get("weather").getAsString();
        double tempF = observationAsJsonObject.get("temp_f").getAsDouble();

        final CurrentObservation currentObservation = new CurrentObservation();
        currentObservation.observationTime = observationTime;
        currentObservation.weather = weather;
        currentObservation.tempF = tempF;

        return new Gson().fromJson(observation, CurrentObservation.class);
    }

}
