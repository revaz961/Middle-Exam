package com.example.exam.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam.adapter.EpisodeCharacterAdapter
import com.example.exam.api.ResultHandler
import com.example.exam.api.RetrofitService
import com.example.exam.api.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseViewModel : ViewModel() {

    suspend fun <T> post(
        liveData: MutableLiveData<ResultHandler<T>>,
        response: Response<T>
    ): T {
        val body = response.body()
        if (response.isSuccessful) {
            liveData.postValue(ResultHandler.Success<T>(body))
        }
        return body!!
    }

    fun getCharacters(adapter: EpisodeCharacterAdapter, list: List<String>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val id = list.fold("") { acc, elem ->
                    acc + elem.substring(elem.lastIndexOf("/") + 1) + ","
                }.dropLast(1)
                when {
                    id.contains(',') -> {
                        val response = RetrofitService.retrofitService.getCharactersById(id)
                        if (response.isSuccessful) {
                            val data = response.body()
                            withContext(Dispatchers.Main) {
                                adapter.setCharacter(data!!)
                            }
                        }
                    }
                    id.isNotEmpty() -> {
                        val response = RetrofitService.retrofitService.getCharacter(id)
                        if (response.isSuccessful) {
                            val data = response.body()
                            withContext(Dispatchers.Main) {
                                adapter.setCharacter(listOf(data!!))
                            }
                        }
                    }
                }
            }
        }
    }
}