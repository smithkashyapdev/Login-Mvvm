package com.development.loginmvvm.viewmodel


import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.development.loginmvvm.data.network.repository.UserRepository
import com.development.loginmvvm.model.LoginUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    var EmailAddress = MutableLiveData<String>("eve.holt@reqres.in")
    var Password = MutableLiveData<String>("cityslicka")
    var userMutableLiveData: MutableLiveData<LoginUser>

    init {
        userMutableLiveData=MutableLiveData()
    }

    val user: MutableLiveData<LoginUser>
        get() {
            if (userMutableLiveData == null) {
                userMutableLiveData = MutableLiveData()
            }
            return userMutableLiveData
        }

    fun onClick(view: View?) {
        val loginUser = Password.value?.let { EmailAddress.value?.let { it1 -> LoginUser(it1, it) } }
        userMutableLiveData!!.setValue(loginUser)
    }

    suspend fun userLogin(loginUser: LoginUser
    ) = withContext(Dispatchers.IO) { repository.userLogin(loginUser.strEmailAddress, loginUser.strPassword) }



}