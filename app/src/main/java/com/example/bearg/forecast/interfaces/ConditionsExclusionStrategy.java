package com.example.bearg.forecast.interfaces;

import com.example.bearg.forecast.model.currentconditions.Estimated;
import com.example.bearg.forecast.model.currentconditions.ObservationLocation;
import com.example.bearg.forecast.model.currentconditions.Response;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bearg on 6/4/2016.
 */
public class ConditionsExclusionStrategy implements ExclusionStrategy {

    // exclude these classes from being deserialized from JSON to POJOs
    private final List<Class> classesToExclude;

    // exclude all but these fields from being deserialized
    private final List<String> fieldsNotToExclude;

    public ConditionsExclusionStrategy() {
        classesToExclude = new ArrayList<>();
        classesToExclude.add(Response.class);
        classesToExclude.add(ObservationLocation.class);
        classesToExclude.add(Estimated.class);

        fieldsNotToExclude = new ArrayList<>();
        fieldsNotToExclude.add("observation_time");
        fieldsNotToExclude.add("weather");
        fieldsNotToExclude.add("temp_f");
        fieldsNotToExclude.add("relative_humidity");
        fieldsNotToExclude.add("wind_string");
        fieldsNotToExclude.add("wind_dir");
        fieldsNotToExclude.add("pressure_in");
        fieldsNotToExclude.add("dewpoint_f");
        fieldsNotToExclude.add("feelslike_f");

    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        String fieldName = f.toString();
        // don't skip if the field name is in our list of fields not to skip
        if (fieldsNotToExclude.contains(fieldName)) {
            return false;
        }

        // otherwise skip that field
        return true;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        if (classesToExclude.contains(clazz)) {
            return true;
        }

        return false;
    }



}
