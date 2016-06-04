package com.example.bearg.forecast.model;

/**
 * Created by bearg on 6/3/2016.
 * Model class for JSON response from wunderground.com
 */
public class CurrentObservation {

    private DisplayLocation location;
    private String observationTime;
    private String weather;
    private String tempF;
    private String relativeHumidity;
    private String windString;
    private String windDir;
    private double windMph;
    private double windGustMph;
    private String pressureIn;
    private int dewpointF;
    private String precip1hrIn;
    private String precipTodayIn;
}
