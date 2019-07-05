package com.hard.code.tech.myretrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryInfo {

    @SerializedName("RestResponse")
    @Expose
    private CountryResponse restResponse;

    public CountryResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(CountryResponse restResponse) {
        this.restResponse = restResponse;
    }
}
