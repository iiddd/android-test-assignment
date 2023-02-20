package com.iiddd.abnamrorepos.ui

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.usecase.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    getRepos: GetReposUseCase
) : ViewModel() {

    val repos: Flow<PagingData<Repo>> =
        getRepos().cachedIn(viewModelScope)
}