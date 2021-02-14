package com.development.loginmvvm.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.development.loginmvvm.R
import com.development.loginmvvm.SoftInputAssist
import com.development.loginmvvm.data.network.ApiException
import com.development.loginmvvm.data.network.NoInternetException
import com.development.loginmvvm.data.network.repository.UserRepository
import com.development.loginmvvm.databinding.ActivityMainBinding
import com.development.loginmvvm.viewmodel.loginmodel.LoginViewModel
import com.development.loginmvvm.viewmodel.loginmodel.LoginViewModelFactory
import kotlinx.coroutines.*
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi


class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var factory: LoginViewModelFactory
    private var binding: ActivityMainBinding? = null
    private var mSoftInputAssist: SoftInputAssist?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val api=MyApi.invoke()
        factory= LoginViewModelFactory(UserRepository(api = api))
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding!!.lifecycleOwner = this;

        binding!!.loginViewModel = loginViewModel;

        loginViewModel.testLiveData.observe(this,{
            Log.e("testLiveData",it)
            doSomething()
        })

        loginViewModel!!.user.observe(this,
            { loginUser ->
                if (TextUtils.isEmpty(loginUser?.strEmailAddress)) {
                    binding!!.emailInputLayout3.setError("Enter an E-Mail Address")
                    binding!!.emailInputLayout3.requestFocus()
                } else if (!loginUser.isEmailValid) {
                    binding!!.emailInputLayout3.setError("Enter a Valid E-mail Address")
                    binding!!.emailInputLayout3.requestFocus()
                } else if (TextUtils.isEmpty(loginUser.strPassword)) {
                    binding!!.passwordInputLayout.setError("Enter a Password")
                    binding!!.passwordInputLayout.requestFocus()
                } else if (!loginUser.isPasswordLengthGreaterThan5) {
                    binding!!.passwordInputLayout.setError("Enter at least 6 Digit password")
                    binding!!.passwordInputLayout.requestFocus()
                } else {

                    lifecycleScope.launch {
                        try {
                            val authResponse = loginViewModel.userLogin(loginUser)
                            if (authResponse.token != null) {
                                Toast.makeText(this@MainActivity,"loggedIN",Toast.LENGTH_LONG).show()
                                delay(1000)
                                Intent(this@MainActivity, UserDetailActivity::class.java).let {
                                    startActivity(it)
                                    finish()
                                }
                            } else {
                                Toast.makeText(this@MainActivity,"error",Toast.LENGTH_LONG).show()
                            }

                        } catch (e: ApiException) {
                            e.printStackTrace()
                            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
                        } catch (e: NoInternetException) {
                            e.printStackTrace()
                            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
                        }
                    }


                }
            })
        mSoftInputAssist= SoftInputAssist(this)
    }


    override fun onPause() {
        mSoftInputAssist?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mSoftInputAssist?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mainScope?.cancel()
        mSoftInputAssist?.onDestroy()
        var mJob = Job()
        var viewModelScope = CoroutineScope(Dispatchers.IO + mJob)
        viewModelScope.launch {
            delay(3000)
            Log.e("onDestroy","onDestroy")
        }
        Log.e("onDestroy->","onDestroy")
        super.onDestroy()
    }

    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        runBlocking {
            for (i in 0..10) {
                val job = async {
                    delay(1)
                    println(i)
                }

                //job.join()
            }
        }
    }
}