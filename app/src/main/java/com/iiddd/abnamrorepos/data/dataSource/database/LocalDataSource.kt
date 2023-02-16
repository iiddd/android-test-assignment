package com.iiddd.abnamrorepos.data.dataSource.database

import com.iiddd.abnamrorepos.data.dataSource.database.dao.RepoDao
import com.iiddd.abnamrorepos.data.mapper.DatabaseMapper
import com.iiddd.abnamrorepos.domain.entity.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val repoDao: RepoDao,
    private val mapper: DatabaseMapper
) {
    suspend fun mergeOrInsert(vararg repos: Repo) {
        for (repo in repos) {
            val currentRepo = repoDao.getById(id = repo.id)
            val newRepo = mapper.fromDomain(repo)

            repoDao.insert(mapper.merge(currentRepo, newRepo))
        }
    }

    fun getById(id: Int) = repoDao.getPojoById(id).map { mapper.toDomain(it) }

    fun getAll(limit: Int): Flow<List<Repo>> = repoDao
        .getAll(limit)
        .map { list -> list.map(mapper::toDomain) }
}