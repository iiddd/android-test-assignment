package com.iiddd.abnamrorepos.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iiddd.abnamrorepos.domain.entity.Repo
import kotlin.properties.Delegates

class RepoViewHolder(
    itemView: View,
    onRepoClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private var repoId by Delegates.notNull<Int>()

    init {
        itemView.setOnClickListener {
            onRepoClickListener(repoId)
        }
    }

    fun bind(repo: Repo) {
        repoId = repo.id
    }
}