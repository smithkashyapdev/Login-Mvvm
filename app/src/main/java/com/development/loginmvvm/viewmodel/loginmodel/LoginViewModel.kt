package com.development.loginmvvm.viewmodel.loginmodel


import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.development.loginmvvm.data.network.repository.UserRepository
import com.development.loginmvvm.model.LoginUser
import kotlinx.coroutines.*


class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    var EmailAddress = MutableLiveData<String>("eve.holt@reqres.in")
    var Password = MutableLiveData<String>("cityslicka")
    var userMutableLiveData: MutableLiveData<LoginUser>
    var testLiveData = MutableLiveData<String>("cityslicka")
    var testDataFromRepo = MutableLiveData<String>("0")
    var mHandler= Handler()
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
        /*val loginUser = Password.value?.let { EmailAddress.value?.let { it1 -> LoginUser(it1, it) } }
        userMutableLiveData!!.value = loginUser*/
        getData()
        mHandler.postDelayed({  testLiveData.value="Patiala"},3000)


    }



    suspend fun userLogin(loginUser: LoginUser) = withContext(Dispatchers.IO) {
        repository.userLogin(loginUser.strEmailAddress, loginUser.strPassword)
    }


    fun getData(){
        var request:Job?=null
        testDataFromRepo.observeForever {
            Log.e("observeForever", it)
            request?.cancel() // cancel processing of the request
            println("main: Who has survived request cancellation?")
        }
        request=GlobalScope.launch {
            repository.testWithDelay(testDataFromRepo)

            launch { // context of the parent, main runBlocking coroutine
                println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
                println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
                println("Default               : I'm working in thread ${Thread.currentThread().name}")
            }
            launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
                println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
            }


            val a = async {
                delay(1000)
                println("A I'm computing a piece of the answer")
                6
            }
            val b = async {
                println("B I'm computing another piece of the answer")
                7
            }
            println("The answer is ${a.await() * b.await()}")
        }

    }




}