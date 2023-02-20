package com.iiddd.abnamrorepos.domain.repository

import androidx.paging.PagingData
import com.iiddd.abnamrorepos.domain.entity.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getById(repoId: Int): Flow<Repo>

    fun getPagedRepos(): Flow<PagingData<Repo>>
}