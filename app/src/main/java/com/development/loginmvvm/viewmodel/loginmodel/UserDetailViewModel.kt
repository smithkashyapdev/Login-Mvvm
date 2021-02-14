package com.development.loginmvvm.viewmodel.loginmodel

import androidx.lifecycle.ViewModel
import com.development.loginmvvm.data.network.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDetailViewModel(private val repository: UserRepository) : ViewModel(){

    suspend fun getUserDetail() = withContext(Dispatchers.IO) { repository.userDetail("1") }



}