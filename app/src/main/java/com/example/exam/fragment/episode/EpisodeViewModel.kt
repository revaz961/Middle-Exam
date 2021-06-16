package com.example.exam.fragment.episode

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.exam.adapter.EpisodePageSource
import com.example.exam.viewmodel.ResultViewModel

class EpisodeViewModel : ResultViewModel() {



    val episodeList = Pager(
        PagingConfig(
            pageSize = 20
        )
    ) {
        EpisodePageSource()
    }.liveData





//    private val _episodeLiveData = MutableLiveData<ResultHandler<Episode>>()
//    val episodeLiveData: LiveData<ResultHandler<Episode>> = _episodeLiveData
//
//    private val _episodesLiveData = MutableLiveData<ResultHandler<PageResult<Episode>>>()
//    val episodesLiveData: LiveData<ResultHandler<PageResult<Episode>>> = _episodesLiveData

//    fun getEpisode(id: String = "", options: Map<String, String> = mapOf()) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                getResult(EndPoint.EPISODE.endPoint, id, options)
//            }
//        }
//    }
//
//    private suspend fun getResult(endpoint: String, id: String, options: Map<String, String>) {
//
//        if (id.isNotEmpty() && !id.contains(',')) {
//            post<Episode>(
//                _episodeLiveData,
//                RetrofitService.retrofitService.getEpisode(id)
//            )
//        } else
//            post<PageResult<Episode>>(
//                _episodesLiveData,
//                RetrofitService.retrofitService.getEpisodes(id, options = options)
//            )
//    }
}