
package com.example.bearg.forecast.model.threedayforecast;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Forecast {

    @SerializedName("txt_forecast")
    @Expose
    public TxtForecast txtForecast;

    @SerializedName("simpleforecast")
    @Expose(deserialize = false)
    public Simpleforecast simpleforecast;

}
