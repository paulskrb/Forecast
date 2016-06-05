
package com.example.bearg.forecast.model.currentconditions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CurrentConditions {

    @SerializedName("response")
    @Expose
    public Response response;
    @SerializedName("current_observation")
    @Expose
    public CurrentObservation currentObservation;

}
