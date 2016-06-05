
package com.example.bearg.forecast.model.threedayforecast;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ThreeDayForecast {

    @SerializedName("response")
    @Expose
    public Response response;
    @SerializedName("getForecast")
    @Expose
    public Forecast forecast;

}
