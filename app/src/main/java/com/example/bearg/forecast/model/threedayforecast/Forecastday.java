
package com.example.bearg.forecast.model.threedayforecast;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Forecastday {

    // used
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("fcttext")
    @Expose
    public String fcttext;
    @SerializedName("icon_url")
    @Expose
    public String iconUrl;

    // unused
    @SerializedName("period")
    @Expose(deserialize = false)
    public Integer period;
    @SerializedName("icon")
    @Expose(deserialize = false)
    public String icon;
    @SerializedName("fcttext_metric")
    @Expose(deserialize = false)
    public String fcttextMetric;
    @SerializedName("pop")
    @Expose(deserialize = false)
    public String pop;

}
