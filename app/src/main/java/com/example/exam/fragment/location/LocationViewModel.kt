package com.example.exam.fragment.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.exam.adapter.EpisodePageSource
import com.example.exam.adapter.LocationPageSource
import com.example.exam.api.ResultHandler
import com.example.exam.api.RetrofitService
import com.example.exam.api.model.Location
import com.example.exam.api.model.PageResult
import com.example.exam.common.IPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocationViewModel : ViewModel(), IPost {

    private val _locationLiveData = MutableLiveData<ResultHandler<Location>>()
    val locationLiveData: LiveData<ResultHandler<Location>> = _locationLiveData

    private val _locationsLiveData = MutableLiveData<ResultHandler<PageResult<Location>>>()
    val locationsLiveData: LiveData<ResultHandler<PageResult<Location>>> = _locationsLiveData

    val locationList = Pager(
        PagingConfig(
            pageSize = 20
        )
    ) {
        LocationPageSource()
    }.liveData

    fun getLocation(id: String = "", options: Map<String, String> = mapOf()) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getResult(id, options)
            }
        }
    }

    private suspend fun getResult(id: String, options: Map<String, String>) {

        if (id.isNotEmpty() && !id.contains(',')) {
            post<Location>(
                _locationLiveData,
                RetrofitService.retrofitService.getLocation(id)
            )
        } else
            post<PageResult<Location>>(
                _locationsLiveData,
                RetrofitService.retrofitService.getLocations(id, options = options)
            )
    }
}