package com.development.loginmvvm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.development.loginmvvm.data.network.ApiException
import com.development.loginmvvm.data.network.NoInternetException
import com.development.loginmvvm.data.network.repository.UserRepository
import com.development.loginmvvm.databinding.ActivityMainBinding
import com.development.loginmvvm.model.LoginUser
import com.development.loginmvvm.viewmodel.LoginViewModel
import com.development.loginmvvm.viewmodel.LoginViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi
import net.simplifiedcoding.mvvmsampleapp.data.network.NetworkConnectionInterceptor


class MainActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var factory: LoginViewModelFactory
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val api=MyApi.invoke()
        factory= LoginViewModelFactory(UserRepository(api = api))
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding!!.setLifecycleOwner(this);

        binding!!.setLoginViewModel(loginViewModel);


        loginViewModel!!.user.observe(this, object : Observer<LoginUser> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onChanged(@Nullable loginUser: LoginUser) {
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
                                Intent(this@MainActivity,UserDetailActivity::class.java).let {
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
            }
        })
    }
}