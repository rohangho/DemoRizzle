package com.demosample.demorizzle.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse {
    @SerializedName("data")
    @Expose
    var data: DataModel? = null
}