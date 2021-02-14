package com.development.loginmvvm.viewmodel.itunemodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.development.loginmvvm.data.network.ApiException
import com.development.loginmvvm.data.network.NoInternetException
import com.development.loginmvvm.data.network.repository.ItuneRepository
import com.development.loginmvvm.model.itunes.ItuneResponse
import com.development.loginmvvm.model.itunes.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ItuneViewModel(private val repository: ItuneRepository) : ViewModel() {

    var progress = MutableLiveData<Int>(0)
    var selectedTab = MutableLiveData<Int>(0)
    var iTuneResponse=MutableLiveData<ItuneResponse>()
    var error=MutableLiveData<Throwable>()



    suspend fun getItuneService() = withContext(Dispatchers.IO) {

        try {
            progress.postValue(0)
            val response = repository.getPreviewList()

            iTuneResponse.postValue(response)

        } catch (e: ApiException) {
            e.printStackTrace()
            error.postValue(e)
        } catch (e: NoInternetException) {
            e.printStackTrace()
            error.postValue(e)
        } catch (e:Exception){
            e.printStackTrace()
            error.postValue(e)
        }
        finally {
            progress.postValue(8)
        }
    }

   suspend fun insertCollectionList(mList:List<Result>)= withContext(Dispatchers.IO) {
        repository.insertData(mList)
    }


     fun getHistoryData(): LiveData<List<Result>>? {
       return repository.getDBHistoryLiveData()
    }


    suspend fun updateRow(mResult:Result)= withContext(Dispatchers.IO) {
       repository.updateRow(mResult)
    }


}