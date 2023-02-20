package com.iiddd.abnamrorepos.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iiddd.abnamrorepos.databinding.ItemRepoBinding
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.utils.StringUtils
import kotlin.properties.Delegates

class RepoViewHolder(
    private val binding: ItemRepoBinding,
    onRepoClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var repoId by Delegates.notNull<Int>()

    init {
        itemView.setOnClickListener {
            onRepoClickListener(repoId)
        }
    }

    fun bind(repo: Repo) {
        repoId = repo.id
        binding.apply {
            nameTextView.text = repo.name
            visibilityValueTextView.text = StringUtils.capitalize(repo.visibility)
            isPrivateValueTextView.text = StringUtils.getIsPrivateFriendlyString(repo.isPrivate)
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