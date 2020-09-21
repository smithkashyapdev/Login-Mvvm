package com.development.loginmvvm.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("first_name")
    var firstName: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("last_name")
    var lastName: String?
){

    override fun toString(): String {
        return "avatar = ${this.avatar}\n"+"email = ${this.email}\n"+"firstName = ${this.firstName}\n"+"firstName = ${this.id}\n"+"lastName = ${this.lastName}\n"
    }

}