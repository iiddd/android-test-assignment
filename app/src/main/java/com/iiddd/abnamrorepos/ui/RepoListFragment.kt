package com.iiddd.abnamrorepos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.iiddd.abnamrorepos.R
import com.iiddd.abnamrorepos.ui.base.BaseFragment
import com.iiddd.abnamrorepos.ui.list.ReposAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that displays repo list.
 */
@AndroidEntryPoint
class RepoListFragment : BaseFragment() {

    private val viewModel by lazy { getViewModel<RepoListViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_repos, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRecyclerView()
    }

    private fun bindRecyclerView() {
//        val adapter = ReposAdapter(::navigateToRepo)

//        observe(viewModel.repos) { adapter.submitList(it) }
    }
}