package com.monofire.movietrailerapp.ui.search.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.monofire.movietrailerapp.data.model.Movie
import com.monofire.movietrailerapp.ui.search.viewholder.MovieViewHolder

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:34          │
//└─────────────────────────────┘
class MovieSearchAdapter : PagingDataAdapter<Movie, MovieViewHolder>(DataDifferntiator) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.createHolder(parent)
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}