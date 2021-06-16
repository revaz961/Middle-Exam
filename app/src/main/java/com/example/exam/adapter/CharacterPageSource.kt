package com.example.exam.adapter

import android.util.Log.d
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.exam.network.RetrofitService
import com.example.exam.model.Character

class CharacterPageSource() :
    PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        return try {
            val currentPage: Int = params.key ?: 1
            d("PagingCharacter",currentPage.toString())
            val pageSize: Int = params.loadSize
            val data = mutableListOf<Character>()
            val response = RetrofitService.retrofitService.getCharacters(page = currentPage)
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
}