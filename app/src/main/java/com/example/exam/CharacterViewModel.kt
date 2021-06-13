package com.example.exam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.exam.adapter.EpisodePageSource
import com.example.exam.adapter.MyPageSource
import com.example.exam.api.EndPoint
import com.example.exam.api.ResultHandler
import com.example.exam.api.RetrofitService
import com.example.exam.api.model.Character
import com.example.exam.api.model.Episode
import com.example.exam.api.model.Location
import com.example.exam.api.model.PageResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CharacterViewModel : ViewModel() {

    val characterList = Pager(PagingConfig(
        pageSize = 20
    )){
        MyPageSource()
    }.liveData

    val episodeList = Pager(PagingConfig(
        pageSize = 20
    )){
        EpisodePageSource()
    }.liveData

    private val _characterLiveData = MutableLiveData<ResultHandler<Character>>()
    val characterLiveData: LiveData<ResultHandler<Character>> = _characterLiveData

    private val _charactersLiveData = MutableLiveData<ResultHandler<PageResult<Character>>>()
    val charactersLiveData: LiveData<ResultHandler<PageResult<Character>>> = _charactersLiveData

    private val _locationLiveData = MutableLiveData<ResultHandler<Location>>()
    val locationLiveData: LiveData<ResultHandler<Location>> = _locationLiveData

    private val _locationsLiveData = MutableLiveData<ResultHandler<PageResult<Location>>>()
    val locationsLiveData: LiveData<ResultHandler<PageResult<Location>>> = _locationsLiveData

    private val _episodeLiveData = MutableLiveData<ResultHandler<Episode>>()
    val episodeLiveData: LiveData<ResultHandler<Episode>> = _episodeLiveData

    private val _episodesLiveData = MutableLiveData<ResultHandler<PageResult<Episode>>>()
    val episodesLiveData: LiveData<ResultHandler<PageResult<Episode>>> = _episodesLiveData

    fun getResult(point: String, id: String = "", options: Map<String, String> = mapOf()) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (point) {
                    EndPoint.CHARACTER.getEndPoint() -> getCharacter(point, id, options)
                    EndPoint.LOCATION.getEndPoint() -> getLocation(point, id, options)
                    EndPoint.EPISODE.getEndPoint() -> getEpisode(point, id, options)
                }
            }
        }
    }


    private suspend fun getCharacter(point: String, id: String, options: Map<String, String>) {

        if (id.isNotEmpty() && !id.contains(',')) {
            post<Character>(
                _characterLiveData,
                RetrofitService.retrofitService.getCharacter(id)
            )
        }
        else
            post<PageResult<Character>>(
                _charactersLiveData,
                RetrofitService.retrofitService.getCharacters(id,options= options)
            )
    }


    private suspend fun getEpisode(point: String, id: String, options: Map<String, String>) {

        if (id.isNotEmpty() && !id.contains(',')) {
            post<Episode>(
                _episodeLiveData,
                RetrofitService.retrofitService.getEpisode(id)
            )
        }
        else
            post<PageResult<Episode>>(
                _episodesLiveData,
                RetrofitService.retrofitService.getEpisodes(id,options= options)
            )
    }


    private suspend fun getLocation(point: String, id: String, options: Map<String, String>) {

        if (id.isNotEmpty() && !id.contains(',')) {
            post<Location>(
                _locationLiveData,
                RetrofitService.retrofitService.getLocation(id)
            )
        }
        else
            post<PageResult<Location>>(
                _locationsLiveData,
                RetrofitService.retrofitService.getLocations(id,options= options)
            )
    }




    private suspend fun <T> post(
        liveData: MutableLiveData<ResultHandler<T>>,
        response: Response<T>
    ) {
        val body = response.body()
        if (response.isSuccessful) {
            liveData.postValue(ResultHandler.Success<T>(body))
        }
    }
}