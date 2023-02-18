package com.iiddd.abnamrorepos.domain.repository

import com.iiddd.abnamrorepos.domain.entity.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RepoRepository {

    fun getAll(limit: Int): Flow<List<Repo>>
    fun getById(repoId: Int): Flow<Repo>
}