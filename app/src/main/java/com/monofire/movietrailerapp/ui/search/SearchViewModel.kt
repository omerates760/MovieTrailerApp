package com.monofire.movietrailerapp.ui.search

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.monofire.movietrailerapp.data.repository.MovieRepository
import com.monofire.movietrailerapp.util.DUMMY_SEARCH_QUERY

class SearchViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _query = MutableLiveData<String>()

    init {
        _query.value = DUMMY_SEARCH_QUERY
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    val results = Transformations.switchMap(_query) { newQuery ->
        repository.fetchSearchMovie(newQuery)
            .cachedIn(viewModelScope).asLiveData()
    }
}