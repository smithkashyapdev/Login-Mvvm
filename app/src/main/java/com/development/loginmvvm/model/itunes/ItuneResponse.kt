package com.development.loginmvvm.model.itunes


import com.google.gson.annotations.SerializedName

data class ItuneResponse(
    @SerializedName("resultCount")
    var resultCount: Int?,
    @SerializedName("results")
    var results: List<Result>?
)