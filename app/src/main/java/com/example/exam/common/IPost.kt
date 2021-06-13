package com.example.exam.common

import androidx.lifecycle.MutableLiveData
import com.example.exam.api.ResultHandler
import retrofit2.Response

interface IPost {
     suspend fun <T> post(
        liveData: MutableLiveData<ResultHandler<T>>,
        response: Response<T>
    ):T {
        val body = response.body()
        if (response.isSuccessful) {
            liveData.postValue(ResultHandler.Success<T>(body))
        }
        return body!!
    }
}