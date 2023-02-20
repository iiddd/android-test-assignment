package com.iiddd.abnamrorepos.data.repository

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.iiddd.abnamrorepos.data.dataSource.api.RemoteDataSource
import com.iiddd.abnamrorepos.data.dataSource.database.LocalDataSource
import com.iiddd.abnamrorepos.data.paging.RepoPageLoader
import com.iiddd.abnamrorepos.data.paging.ReposPagingSource
import com.iiddd.abnamrorepos.domain.entity.Repo
import com.iiddd.abnamrorepos.domain.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,

    ) : RepoRepository {

    override fun getById(repoId: Int) = flow {
        emitAll(localDataSource.getById(repoId))
    }

    override fun getPagedRepos(): Flow<PagingData<Repo>> {
        val loader: RepoPageLoader = { pageIndex, pageSize ->
            getRepos(pageIndex, pageSize)
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE / 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReposPagingSource(loader) }
        ).flow
    }

    @VisibleForTesting
    private fun List<Repo>.sortByName() = this.sortedBy { it.name }

    private suspend fun getRepos(pageIndex: Int, pageSize: Int): List<Repo> =
        withContext(Dispatchers.IO) {
            val remoteRepos = remoteDataSource.get(pageIndex, pageSize)
            remoteRepos.forEach { localDataSource.mergeOrInsert(it) }
            val offset = calculateOffset(pageIndex, pageSize)
            return@withContext localDataSource.get(
                limit = pageSize,
                offset = offset
            )
                .sortByName()
        }

    private fun calculateOffset(pageIndex: Int, pageSize: Int): Int {
        return if (pageIndex == 1) {
            0
        } else pageSize * (pageIndex - 1)
    }

    private companion object {
        const val PAGE_SIZE = 15
    }
}