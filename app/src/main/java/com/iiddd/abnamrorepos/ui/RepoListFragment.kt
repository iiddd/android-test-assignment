package com.iiddd.abnamrorepos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iiddd.abnamrorepos.data.paging.simpleScan
import com.iiddd.abnamrorepos.databinding.FragmentReposBinding
import com.iiddd.abnamrorepos.ui.list.loadState.LoadStateAdapter
import com.iiddd.abnamrorepos.ui.list.loadState.LoadStateViewHolder
import com.iiddd.abnamrorepos.ui.list.repolist.ReposAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Fragment that displays repo list.
 */
@AndroidEntryPoint
class RepoListFragment : Fragment() {

    private lateinit var binding: FragmentReposBinding
    private lateinit var adapter: ReposAdapter
    private val viewModel: RepoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentReposBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRecyclerView()
        setupLoadState()
        setupSwipeToRefresh()
        observeLoadState()
        handleListVisibility()
    }

    private fun bindRecyclerView() {
        adapter = ReposAdapter(::navigateToRepo)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.repos.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun handleListVisibility() {
        lifecycleScope.launchWhenCreated {
            getRefreshLoadStateFlow(adapter)
                .simpleScan(count = 3)
                .collectLatest { (beforePrevious, previous, current) ->
                    binding.recyclerView.isInvisible = current is LoadState.Error
                            || previous is LoadState.Error
                            || (beforePrevious is LoadState.Error && previous is LoadState.NotLoading
                            && current is LoadState.Loading)
                }
        }
    }

    private fun setupLoadState() {
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            LoadStateAdapter {
                adapter.retry()
            }
        )
        (binding.recyclerView.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations =
            false
    }

    private fun getRefreshLoadStateFlow(adapter: ReposAdapter): Flow<LoadState> {
        return adapter.loadStateFlow
            .map { it.refresh }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            adapter.loadStateFlow.debounce(200).collectLatest { state ->
                LoadStateViewHolder(
                    { adapter.retry() },
                    binding.loadStateView,
                    null
                ).bind(state.refresh)
            }
        }
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun navigateToRepo(repoId: Int) {
        val direction = RepoListFragmentDirections.goToRepo(repoId)
        findNavController().navigate(direction)
    }
}