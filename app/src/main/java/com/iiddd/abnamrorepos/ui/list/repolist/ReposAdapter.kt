package com.iiddd.abnamrorepos.ui.list.repolist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.iiddd.abnamrorepos.databinding.ItemRepoBinding
import com.iiddd.abnamrorepos.domain.entity.Repo

class ReposAdapter(
    private val onRepoClickListener: (Int) -> Unit
) : PagingDataAdapter<Repo, RepoViewHolder>(diffCallback) {

    private lateinit var binding: ItemRepoBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRepoBinding.inflate(inflater, parent, false)
        context = parent.context
        return RepoViewHolder(binding, onRepoClickListener)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) =
        holder.bind(getItem(position)!!)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.name == newItem.name && oldItem.fullName == newItem.fullName
            }
        }
    }
}

