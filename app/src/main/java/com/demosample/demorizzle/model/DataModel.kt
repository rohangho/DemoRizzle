package com.demosample.demorizzle.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataModel {
    @SerializedName("celebrities")
    @Expose
    var celebrities: Celebrities? = null

    @SerializedName("cars")
    @Expose
    var cars: Cars? = null
}