package com.demosample.demorizzle.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NodeModel {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("feature")
    @Expose
    var feature: String? = null

    @SerializedName("netWorth")
    @Expose
    var netWorth: String? = null
}