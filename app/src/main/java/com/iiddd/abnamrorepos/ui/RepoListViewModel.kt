package com.iiddd.abnamrorepos.ui

import androidx.lifecycle.*
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.usecase.GetRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val getRepos: GetRepoUseCase
) : ViewModel() {

    val repos: LiveData<List<Repo>> = getRepos.invoke(100).asLiveData()
}