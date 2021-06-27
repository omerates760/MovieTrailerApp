package com.monofire.movietrailerapp.ui.search.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.monofire.movietrailerapp.data.model.Movie
import com.monofire.movietrailerapp.databinding.ListItemMovieBinding
import com.monofire.movietrailerapp.ui.search.SearchFragmentDirections

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:44          │
//└─────────────────────────────┘
class MovieViewHolder(private val binding: ListItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createHolder(parent: ViewGroup): MovieViewHolder {
            val binding =
                ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MovieViewHolder(binding)
        }
    }

    fun bind(movie: Movie) {
        binding.movie = movie
        binding.root.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                binding.imgMoviePoster to "media_transition"
            )
            val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(
                movie.title?: "",
                movie
            )
            Navigation.findNavController(binding.root).navigate(action, extras)
        }
    }
}