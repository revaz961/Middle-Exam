package com.example.exam.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.exam.network.RetrofitService
import com.example.exam.model.Location

class LocationPageSource() :
    PagingSource<Int, Location>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {

        return try {
            val currentPage: Int = params.key ?: 1
            val pageSize: Int = params.loadSize
            val data = mutableListOf<Location>()
            val response = RetrofitService.retrofitService.getLocations(page = currentPage)
            val body = response.body()
            val result = body?.results ?: emptyList()
            data.addAll(result)

            LoadResult.Page(
                result,
                if (currentPage == 1) null else currentPage - 1,
                if (currentPage == body?.info?.pages) null else currentPage + 1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition
    }
}