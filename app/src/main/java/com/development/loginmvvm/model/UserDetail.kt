package com.development.loginmvvm.model


import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("ad")
    var ad: Ad?,
    @SerializedName("data")
    var `data`: Data?
){


    override fun toString(): String {
        return "ad = ${this.ad}\n"+"data = ${this.data}\n"
    }

}