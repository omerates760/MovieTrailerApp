package com.monofire.movietrailerapp.data.network.service

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.monofire.movietrailerapp.data.model.Movie
import retrofit2.HttpException
import java.io.IOException

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:15          │
//└─────────────────────────────┘
class MoviePagingSource(
    private val movieApi: MovieApi, private val query: String
) :
    PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = movieApi.fetchSearchMovie(query = query, page = page)
            LoadResult.Page(
                data = response.movies!!,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response?.movies?.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}