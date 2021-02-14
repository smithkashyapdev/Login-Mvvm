package com.development.loginmvvm.data.network.repository

import androidx.lifecycle.LiveData
import com.development.loginmvvm.model.UserDetail
import com.development.loginmvvm.model.itunes.ItuneResponse
import com.development.loginmvvm.model.itunes.Result
import com.development.loginmvvm.room.AppDB
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi
import net.simplifiedcoding.mvvmsampleapp.data.network.SafeApiRequest

class ItuneRepository(
    private val api: MyApi,
    var mDB: AppDB?
) : SafeApiRequest() {


    suspend fun getPreviewList(): ItuneResponse {
        return apiRequest { api.getPreviewList() }
    }

    suspend fun insertData(mData:Result) {
        mDB?.collectionDao()?.insertSingleResultRow(mData)
    }

    suspend fun insertData(mData:List<Result>) {
        mDB?.collectionDao()?.insertAllListResult(mData)
    }

     fun getDBHistoryLiveData(): LiveData<List<Result>>? {
       return mDB?.collectionDao()?.getUpdatedDetails(true)
    }


    suspend fun updateRow(mData:Result) {
        mDB?.collectionDao()?.updateRow(mData)
    }


}