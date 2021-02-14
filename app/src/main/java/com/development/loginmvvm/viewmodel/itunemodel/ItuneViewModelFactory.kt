package com.development.loginmvvm.viewmodel.itunemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.development.loginmvvm.data.network.repository.ItuneRepository
import com.development.loginmvvm.room.AppDB
import com.development.loginmvvm.viewmodel.loginmodel.LoginViewModel

class ItuneViewModelFactory(
    private val repository: ItuneRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItuneViewModel(repository) as T
    }
}