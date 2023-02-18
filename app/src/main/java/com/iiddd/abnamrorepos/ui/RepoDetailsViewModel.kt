package com.iiddd.abnamrorepos.ui

import androidx.lifecycle.*
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.usecase.GetRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getRepo: GetRepoUseCase
) : ViewModel() {

    private val repoId = MutableLiveData<Int>()

    val repo: LiveData<Repo> = repoId.switchMap { getRepo(it).asLiveData() }

    fun setRepoId(repoId: Int) {
        this.repoId.value = repoId
    }
}