package com.development.loginmvvm.viewmodel.loginmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.development.loginmvvm.data.network.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}