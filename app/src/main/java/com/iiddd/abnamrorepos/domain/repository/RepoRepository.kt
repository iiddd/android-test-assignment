package com.iiddd.abnamrorepos.domain.repository

import com.iiddd.abnamrorepos.domain.entity.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    fun getAll(limit: Int): Flow<List<Repo>>
//    fun getById(repoId: Int): Flow<Repo>
}