package com.iiddd.abnamrorepos.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.iiddd.abnamrorepos.R
import com.iiddd.abnamrorepos.domain.entity.Repo

class ReposAdapter(
    private val onRepoClickListener: (Int) -> Unit
) : ListAdapter<Repo, RepoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false),
        onRepoClickListener = onRepoClickListener
    )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.name == newItem.name && oldItem.fullName == newItem.fullName
            }
        }
    }
}

