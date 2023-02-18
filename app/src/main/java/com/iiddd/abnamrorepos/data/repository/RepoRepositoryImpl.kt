package com.iiddd.abnamrorepos.data.repository

import androidx.annotation.VisibleForTesting
import com.iiddd.abnamrorepos.data.dataSource.api.RemoteDataSource
import com.iiddd.abnamrorepos.data.dataSource.database.LocalDataSource
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.repository.RepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RepoRepository {

    override fun getById(repoId: Int) = flow {
        emitAll(localDataSource.getById(repoId))
    }

    override fun getAll(limit: Int) = flow {
        CoroutineScope(currentCoroutineContext()).launch {
            val remoteRepos = remoteDataSource.getAll()
            remoteRepos?.forEach { localDataSource.mergeOrInsert(it) }
        }
        emitAll(localDataSource.getAll(limit).map { list -> list.sortByName() })
    }

    @VisibleForTesting
    private fun List<Repo>.sortByName() = this.sortedBy { it.name }
}