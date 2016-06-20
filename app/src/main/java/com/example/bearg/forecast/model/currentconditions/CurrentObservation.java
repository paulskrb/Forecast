
package com.example.bearg.forecast.model.currentconditions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CurrentObservation {

    // these fields will be used
    @SerializedName("display_location")
    @Expose
    public DisplayLocation displayLocation;

    @SerializedName("observation_time")
    @Expose
    public String observationTime;

    @SerializedName("weather")
    @Expose
    public String weather;

    @SerializedName("temp_f")
    @Expose
    public double tempF;

    @SerializedName("relative_humidity")
    @Expose
    public String relativeHumidity;

    @SerializedName("wind_string")
    @Expose
    public String windString;

    @SerializedName("wind_dir")
    @Expose
    public String windDir;

    @SerializedName("wind_mph")
    @Expose
    public double windMph;

    @SerializedName("wind_gust_mph")
    @Expose
    public double windGustMph;

    @SerializedName("pressure_in")
    @Expose
    public String pressureIn;

    @SerializedName("pressure_trend")
    @Expose
    public String pressureTrend;

    @SerializedName("dewpoint_f")
    @Expose
    public double dewpointF;

    @SerializedName("UV")
    @Expose
    public String uV;

    @SerializedName("precip_1hr_in")
    @Expose
    public String precip1hrIn;

    @SerializedName("precip_today_in")
    @Expose
    public String precipTodayIn;

    @SerializedName("icon_url")
    @Expose
    public String iconUrl;

    @SerializedName("feelslike_f")
    @Expose
    public String feelslikeF;

    // unused
    @SerializedName("image")
    @Expose(deserialize = false)
    public Image image;
    @SerializedName("observation_location")
    @Expose(deserialize = false)
    public ObservationLocation observationLocation;
    @SerializedName("estimated")
    @Expose(deserialize = false)
    public Estimated estimated;
    @SerializedName("station_id")
    @Expose(deserialize = false)
    public String stationId;
    @SerializedName("observation_time_rfc822")
    @Expose(deserialize = false)
    public String observationTimeRfc822;
    @SerializedName("observation_epoch")
    @Expose(deserialize = false)
    public String observationEpoch;
    @SerializedName("local_time_rfc822")
    @Expose(deserialize = false)
    public String localTimeRfc822;
    @SerializedName("local_epoch")
    @Expose(deserialize = false)
    public String localEpoch;
    @SerializedName("local_tz_short")
    @Expose(deserialize = false)
    public String localTzShort;
    @SerializedName("local_tz_long")
    @Expose(deserialize = false)
    public String localTzLong;
    @SerializedName("local_tz_offset")
    @Expose(deserialize = false)
    public String localTzOffset;
    @SerializedName("temperature_string")
    @Expose(deserialize = false)
    public String temperatureString;
    @SerializedName("temp_c")
    @Expose(deserialize = false)
    public double tempC;
    @SerializedName("wind_degrees")
    @Expose(deserialize = false)
    public double windDegrees;
    @SerializedName("wind_kph")
    @Expose(deserialize = false)
    public double windKph;
    @SerializedName("wind_gust_kph")
    @Expose(deserialize = false)
    public double windGustKph;
    @SerializedName("pressure_mb")
    @Expose(deserialize = false)
    public String pressureMb;
    @SerializedName("dewpoint_string")
    @Expose(deserialize = false)
    public String dewpointString;
    @SerializedName("dewpoint_c")
    @Expose(deserialize = false)
    public double dewpointC;
    @SerializedName("heat_index_string")
    @Expose(deserialize = false)
    public String heatIndexString;

    @SerializedName("heat_index_c")
    @Expose(deserialize = false)
    public String heatIndexC;
    @SerializedName("windchill_string")
    @Expose(deserialize = false)
    public String windchillString;

    @SerializedName("windchill_c")
    @Expose(deserialize = false)
    public String windchillC;
    @SerializedName("feelslike_string")
    @Expose(deserialize = false)
    public String feelslikeString;

    @SerializedName("feelslike_c")
    @Expose(deserialize = false)
    public String feelslikeC;
    @SerializedName("visibility_mi")
    @Expose(deserialize = false)
    public String visibilityMi;
    @SerializedName("visibility_km")
    @Expose(deserialize = false)
    public String visibilityKm;
    @SerializedName("solarradiation")
    @Expose(deserialize = false)
    public String solarradiation;

    @SerializedName("precip_1hr_string")
    @Expose(deserialize = false)
    public String precip1hrString;

    @SerializedName("precip_1hr_metric")
    @Expose(deserialize = false)
    public String precip1hrMetric;
    @SerializedName("precip_today_string")
    @Expose(deserialize = false)
    public String precipTodayString;

    @SerializedName("precip_today_metric")
    @Expose(deserialize = false)
    public String precipTodayMetric;
    @SerializedName("icon")
    @Expose(deserialize = false)
    public String icon;

    @SerializedName("forecast_url")
    @Expose(deserialize = false)
    public String forecastUrl;
    @SerializedName("history_url")
    @Expose(deserialize = false)
    public String historyUrl;
    @SerializedName("ob_url")
    @Expose(deserialize = false)
    public String obUrl;
    @SerializedName("nowcast")
    @Expose(deserialize = false)
    public String nowcast;

    @SerializedName("heat_index_f")
    @Expose(deserialize = false)
    public String heatIndexF;

    @SerializedName("windchill_f")
    @Expose(deserialize = false)
    public String windchillF;

}
