package com.example.exam.fragment.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.exam.adapter.EpisodePageSource
import com.example.exam.api.EndPoint
import com.example.exam.api.ResultHandler
import com.example.exam.api.RetrofitService
import com.example.exam.api.model.Episode
import com.example.exam.api.model.PageResult
import com.example.exam.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodeViewModel : BaseViewModel() {

    private val _episodeLiveData = MutableLiveData<ResultHandler<Episode>>()
    val episodeLiveData: LiveData<ResultHandler<Episode>> = _episodeLiveData

    private val _episodesLiveData = MutableLiveData<ResultHandler<PageResult<Episode>>>()
    val episodesLiveData: LiveData<ResultHandler<PageResult<Episode>>> = _episodesLiveData


    val episodeList = Pager(
        PagingConfig(
            pageSize = 20
        )
    ) {
        EpisodePageSource()
    }.liveData


    fun getEpisode(id: String = "", options: Map<String, String> = mapOf()) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getResult(EndPoint.EPISODE.endPoint, id, options)
            }
        }
    }

    private suspend fun getResult(endpoint: String, id: String, options: Map<String, String>) {

        if (id.isNotEmpty() && !id.contains(',')) {
            post<Episode>(
                _episodeLiveData,
                RetrofitService.retrofitService.getEpisode(id)
            )
        } else
            post<PageResult<Episode>>(
                _episodesLiveData,
                RetrofitService.retrofitService.getEpisodes(id, options = options)
            )
    }
}