package com.iiddd.abnamrorepos.ui.list.loadState

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.iiddd.abnamrorepos.databinding.LoadMoreBinding

class LoadStateViewHolder(
    retry: () -> Unit,
    private val binding: LoadMoreBinding,
    private val swipeRefreshLayout: SwipeRefreshLayout?,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    fun bind(state: LoadState) {
        binding.apply {
            messageTextView.isVisible = state is LoadState.Error
            retryButton.isVisible = state is LoadState.Error
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.isRefreshing = state is LoadState.Loading
                progressBar.isVisible = false
            } else {
                progressBar.isVisible = state is LoadState.Loading
            }
        }
    }
}