package com.iiddd.abnamrorepos.ui

import androidx.lifecycle.*
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.usecase.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    getRepos: GetReposUseCase
) : ViewModel() {

    val repos: LiveData<List<Repo>> =
        getRepos(100).asLiveData()
}