package com.development.loginmvvm.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.development.loginmvvm.model.itunes.Result

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSingleResultRow(mResult: Result)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllListResult(mResult: List<Result>)

    @Query("SELECT * FROM result WHERE isVisited =:isVisited")
    fun getUpdatedDetails(isVisited: Boolean?) : LiveData<List<Result>>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateRow(mResult: Result)

}