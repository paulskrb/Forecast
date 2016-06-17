
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

    // unused
    @SerializedName("period")
    @Expose(deserialize = false)
    public Integer period;
    @SerializedName("icon")
    @Expose(deserialize = false)
    public String icon;
    @SerializedName("icon_url")
    @Expose(deserialize = false)
    public String iconUrl;
    @SerializedName("fcttext_metric")
    @Expose(deserialize = false)
    public String fcttextMetric;
    @SerializedName("pop")
    @Expose(deserialize = false)
    public String pop;

}
