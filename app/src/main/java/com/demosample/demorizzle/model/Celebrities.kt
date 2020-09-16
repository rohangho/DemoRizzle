package com.demosample.demorizzle.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Celebrities {
    @SerializedName("nodes")
    @Expose
    var nodes: List<NodeModel>? = null
}