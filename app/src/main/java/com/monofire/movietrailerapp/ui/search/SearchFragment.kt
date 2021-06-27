package com.monofire.movietrailerapp.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.monofire.movietrailerapp.R
import com.monofire.movietrailerapp.databinding.SearchFragmentBinding
import com.monofire.movietrailerapp.ui.base.BaseFragment
import com.monofire.movietrailerapp.ui.search.adapter.HomeLoadStateAdapter
import com.monofire.movietrailerapp.ui.search.adapter.MovieSearchAdapter
import com.monofire.movietrailerapp.util.ext.queryLengthCheck
import com.monofire.movietrailerapp.util.ext.showErrorMessage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<SearchFragmentBinding>() {
    val viewModel: SearchViewModel by viewModel()
    private lateinit var adapter: MovieSearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        setUpAdapter()
        initSearchObserver()

    }

    private fun setUpAdapter() {
        adapter = MovieSearchAdapter()
        binding.apply {
            rcViewMedia.adapter = adapter.withLoadStateFooter(
                footer = HomeLoadStateAdapter { adapter.retry() }
            )
            adapter.addLoadStateListener { loadState ->
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rcViewMedia.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error

                // No results found
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    rcViewMedia.isVisible = false
                    tvNoSearchResult.isVisible = true
                } else {
                    tvNoSearchResult.isVisible = false
                }
            }
        }

    }

    private fun initSearchObserver() {
        viewModel.results.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.menu_search)
        val search = searchItem.actionView as SearchView
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        search.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (query.queryLengthCheck()) {
                        search.clearFocus()
                        searchItem.collapseActionView()
                        viewModel.updateQuery(query ?: "")
                    } else {
                        showErrorMessage()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(layoutInflater, R.layout.search_fragment, container, false)
    }

}