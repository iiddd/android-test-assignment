package com.iiddd.abnamrorepos.ui

import androidx.lifecycle.ViewModel
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.usecase.GetRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getRepo: GetRepoUseCase
) : ViewModel() {

    private val repoId = MutableStateFlow(0)

    val repo: Flow<Repo> = repoId.flatMapLatest { getRepo(it) }

    fun setRepoId(repoId: Int) {
        this.repoId.value = repoId
    }
}