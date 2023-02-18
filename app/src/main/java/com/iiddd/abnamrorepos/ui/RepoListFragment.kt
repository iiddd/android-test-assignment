package com.iiddd.abnamrorepos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iiddd.abnamrorepos.databinding.FragmentReposBinding
import com.iiddd.abnamrorepos.ui.list.ReposAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that displays repo list.
 */
@AndroidEntryPoint
class RepoListFragment : Fragment() {

    private lateinit var binding: FragmentReposBinding

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
    }

    private fun bindRecyclerView() {
        val adapter = ReposAdapter(::navigateToRepo)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        viewModel.repos.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun navigateToRepo(repoId: Int) {
        val direction = RepoListFragmentDirections.goToRepo(repoId)
        findNavController().navigate(direction)
    }
}