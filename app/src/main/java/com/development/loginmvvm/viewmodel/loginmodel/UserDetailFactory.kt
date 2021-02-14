package com.development.loginmvvm.viewmodel.loginmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.development.loginmvvm.data.network.repository.UserRepository

class UserDetailFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserDetailViewModel(repository) as T
    }
}