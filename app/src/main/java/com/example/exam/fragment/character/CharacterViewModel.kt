package com.example.exam.fragment.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.exam.adapter.CharacterPageSource
import com.example.exam.viewmodel.ResultViewModel

class CharacterViewModel : ResultViewModel() {


    val characterList = Pager(
        PagingConfig(
            pageSize = 20
        )
    ) {
        CharacterPageSource()
    }.liveData





//    private val _characterLiveData = MutableLiveData<ResultHandler<Character>>()
//    val characterLiveData: LiveData<ResultHandler<Character>> = _characterLiveData
//
//    private val _charactersLiveData = MutableLiveData<ResultHandler<PageResult<Character>>>()
//    val charactersLiveData: LiveData<ResultHandler<PageResult<Character>>> = _charactersLiveData

//    fun getCharacter(id: String = "", options: Map<String, String> = mapOf()) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                getResult(id, options)
//            }
//        }
//    }
//
//    private suspend fun getResult(id: String, options: Map<String, String>) {
//
//        if (id.isNotEmpty() && !id.contains(',')) {
//            post<Character>(
//                _characterLiveData,
//                RetrofitService.retrofitService.getCharacter(id)
//            )
//        } else
//            post<PageResult<Character>>(
//                _charactersLiveData,
//                RetrofitService.retrofitService.getCharacters(id, options = options)
//            )
//    }
}