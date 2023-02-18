package com.iiddd.abnamrorepos.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iiddd.abnamrorepos.databinding.ItemRepoBinding
import com.iiddd.abnamrorepos.domain.entity.Repo
import kotlin.properties.Delegates

class RepoViewHolder(
    private val binding: ItemRepoBinding,
//    onRepoClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

//    private var repoId by Delegates.notNull<Int>()

//    init {
//        itemView.setOnClickListener {
////            onRepoClickListener(repoId)
//        }
//    }

    fun bind(repo: Repo) {
//        repoId = repo.id
        binding.apply {
            nameTextView.text = repo.name
            visibilityValueTextView.text = repo.visibility
            isPrivateValueTextView.text = repo.isPrivate.toString()
        }
    }
}