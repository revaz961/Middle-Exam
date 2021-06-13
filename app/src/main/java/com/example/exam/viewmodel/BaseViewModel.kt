package com.example.exam.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.exam.api.ResultHandler
import retrofit2.Response

interface BaseViewModel {
    suspend fun <T> post(
        liveData: MutableLiveData<ResultHandler<T>>,
        response: Response<T>
    ) {
        val body = response.body()
        if (response.isSuccessful) {
            liveData.postValue(ResultHandler.Success<T>(body))
        }
    }
}