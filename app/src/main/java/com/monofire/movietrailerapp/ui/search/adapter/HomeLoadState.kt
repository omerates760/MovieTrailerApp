package com.monofire.movietrailerapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monofire.movietrailerapp.databinding.LayoutLoadStateBinding

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:40          │
//└─────────────────────────────┘
class HomeLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<HomeLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutLoadStateBinding.inflate(inflater)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(
        private val binding: LayoutLoadStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {

            binding.apply {
                btnLoadStateRetry.setOnClickListener { retry.invoke() }
                btnLoadStateRetry.isVisible = loadState !is LoadState.Loading
                tvLoadStateErrorMessage.isVisible = loadState !is LoadState.Loading

            }
        }
    }
}