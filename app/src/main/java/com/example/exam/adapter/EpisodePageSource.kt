package com.example.exam.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.exam.api.RetrofitService
import com.example.exam.api.model.Character
import com.example.exam.api.model.Episode

class EpisodePageSource:
    PagingSource<Int, Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {

        return try {
            val currentPage: Int = params.key ?: 1
            val pageSize: Int = params.loadSize
            val data = mutableListOf<Episode>()
            val response = RetrofitService.retrofitService.getEpisodes(page = currentPage)
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

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition
    }
}