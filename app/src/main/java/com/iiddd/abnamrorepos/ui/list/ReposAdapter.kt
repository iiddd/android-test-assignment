package com.iiddd.abnamrorepos.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iiddd.abnamrorepos.DataBinderMapperImpl
import com.iiddd.abnamrorepos.R
import com.iiddd.abnamrorepos.databinding.FragmentReposBinding
import com.iiddd.abnamrorepos.databinding.ItemRepoBinding
import com.iiddd.abnamrorepos.domain.entity.Repo

class ReposAdapter(
//    private val onRepoClickListener: (Int) -> Unit
) : ListAdapter<Repo, RepoViewHolder>(diffCallback) {

    private lateinit var binding: ItemRepoBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemRepoBinding.inflate(inflater, parent, false)
        context = parent.context
        return RepoViewHolder(
            binding
        )
    }
//        onRepoClickListener = onRepoClickListener


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

