package com.monofire.movietrailerapp.ui.detail.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.monofire.movietrailerapp.R
import com.monofire.movietrailerapp.databinding.MovieDetailFragmentBinding
import com.monofire.movietrailerapp.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailFragment : BaseFragment<MovieDetailFragmentBinding>() {

    private val viewModel: MovieDetailViewModel by viewModel()
    private val movieArgs:MovieDetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        binding.lifecycleOwner = this
        initUI()
    }

    private fun initUI() {
        binding.movie = movieArgs.movieItem
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewDataBinding {
        return DataBindingUtil.inflate(
            layoutInflater,
            R.layout.movie_detail_fragment,
            container,
            false
        )
    }

}