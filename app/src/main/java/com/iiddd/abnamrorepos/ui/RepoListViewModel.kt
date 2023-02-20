package com.iiddd.abnamrorepos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.usecase.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val getRepos: GetReposUseCase
) : ViewModel() {

    var repos: Flow<PagingData<Repo>> = getRepos().cachedIn(viewModelScope)

    fun refresh() {
        repos = getRepos().cachedIn(viewModelScope)
    }
}