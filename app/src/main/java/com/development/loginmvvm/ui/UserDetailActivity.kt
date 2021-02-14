package com.development.loginmvvm.ui

    import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
    import com.bumptech.glide.Glide
    import com.development.loginmvvm.R
    import com.development.loginmvvm.data.network.ApiException
import com.development.loginmvvm.data.network.NoInternetException
import com.development.loginmvvm.data.network.repository.UserRepository
    import com.development.loginmvvm.databinding.ActivityUserDetailBinding
    import com.development.loginmvvm.viewmodel.loginmodel.UserDetailFactory
import com.development.loginmvvm.viewmodel.loginmodel.UserDetailViewModel
import kotlinx.coroutines.launch
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi

class UserDetailActivity:AppCompatActivity() {


    private lateinit var userDetailViewModel: UserDetailViewModel
    private lateinit var factory: UserDetailFactory
    private var binding: ActivityUserDetailBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        val api= MyApi.invoke()
        factory= UserDetailFactory(UserRepository(api = api))
        userDetailViewModel = ViewModelProvider(this, factory).get(UserDetailViewModel::class.java)
    }


    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {

            try {
                binding?.progressbar?.visibility  = View.VISIBLE
                val userDetail = userDetailViewModel.getUserDetail()
                binding?.progressbar?.visibility  = View.GONE
                if (userDetail != null) {
                    binding?.profileView?.visibility  =View.VISIBLE

                    binding?.profileFullName?.setText(userDetail.data?.firstName +" "+ (userDetail.data?.lastName ))
                    binding?.noteText!!.setText(userDetail.ad!!.text)
                    binding?.profileUserCompany!!.setText(userDetail!!.ad!!.company)

                    try {
                        Glide
                            .with(this@UserDetailActivity)
                            .load(userDetail.data?.avatar)
                            .into(binding!!.profilePhoto)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    Toast.makeText(this@UserDetailActivity,"error",Toast.LENGTH_LONG).show()
                }

            } catch (e: ApiException) {
                binding?.progressbar?.visibility  = View.GONE
                e.printStackTrace()
                Toast.makeText(this@UserDetailActivity,e.message, Toast.LENGTH_LONG).show()
            } catch (e: NoInternetException) {
                binding?.progressbar?.visibility  = View.GONE
                e.printStackTrace()
                Toast.makeText(this@UserDetailActivity,e.message, Toast.LENGTH_LONG).show()
            }

        }

    }


}