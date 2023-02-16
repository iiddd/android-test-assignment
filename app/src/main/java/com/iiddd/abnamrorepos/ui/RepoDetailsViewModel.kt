package com.iiddd.abnamrorepos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.usecase.GetRepoUseCase
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor(
    private val getRepo: GetRepoUseCase
): ViewModel() {

    private val repoId = MutableLiveData<Int>()
}