package com.development.loginmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.development.loginmvvm.R
import com.development.loginmvvm.databinding.ActivityCollectionPreviewBinding
import com.development.loginmvvm.model.itunes.Result

class CollectionDetailActivity :AppCompatActivity() {
    private var binding: ActivityCollectionPreviewBinding? = null

    companion object{
        const val KEY_RESULT_DATA="KEY_RESULT_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mResultData = intent?.getParcelableExtra(KEY_RESULT_DATA) as Result
        binding = DataBindingUtil.setContentView(this, R.layout.activity_collection_preview)
        binding!!.lifecycleOwner = this;

        binding!!.result=mResultData
    }




}