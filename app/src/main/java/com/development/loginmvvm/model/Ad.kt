package com.development.loginmvvm.model


import com.google.gson.annotations.SerializedName

data class Ad(
    @SerializedName("company")
    var company: String?,
    @SerializedName("text")
    var text: String?,
    @SerializedName("url")
    var url: String?
){

    override fun toString(): String {
        return "company = ${this.company}\n"+"text = ${this.text}\n"+"url = ${this.url}\n"
    }

}