package com.development.loginmvvm.data.network.repository


import androidx.lifecycle.MutableLiveData
import com.development.loginmvvm.model.UserDetail
import kotlinx.coroutines.delay
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi
import net.simplifiedcoding.mvvmsampleapp.data.network.SafeApiRequest
import net.simplifiedcoding.mvvmsampleapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userDetail(userID: String): UserDetail {
        return apiRequest { api.getUserDetail() }
    }

    suspend fun testWithDelay(testDataFromRepo:MutableLiveData<String>){
        delay(5000)

        testDataFromRepo.postValue("50")
    }



}