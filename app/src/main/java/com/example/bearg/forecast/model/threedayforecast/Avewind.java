
package com.example.bearg.forecast.model.threedayforecast;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Avewind {

    @SerializedName("mph")
    @Expose
    public Integer mph;
    @SerializedName("kph")
    @Expose
    public Integer kph;
    @SerializedName("dir")
    @Expose
    public String dir;
    @SerializedName("degrees")
    @Expose
    public Integer degrees;

}
