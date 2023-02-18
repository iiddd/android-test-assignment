package com.iiddd.abnamrorepos.ui.list

import android.opengl.Visibility
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iiddd.abnamrorepos.databinding.ItemRepoBinding
import com.iiddd.abnamrorepos.domain.entity.Repo

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
        binding.apply {
            nameTextView.text = repo.name
            visibilityValueTextView.text = repo.visibility
            isPrivateValueTextView.text = repo.isPrivate.toString()
            when (repo.imageUrl) {
                null -> ownersAvatarImageView.visibility = View.GONE
                else -> {
                    ownersAvatarImageView.visibility = View.VISIBLE
                    Glide.with(ownersAvatarImageView.context)
                        .load(repo.imageUrl)
                        .into(ownersAvatarImageView)
                }
            }
        }
    }
}