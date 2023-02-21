package com.iiddd.abnamrorepos.data.dataSource.api

import com.iiddd.abnamrorepos.data.mapper.ApiMapper
import com.iiddd.abnamrorepos.domain.entity.Repo
import javax.inject.Inject

open class RemoteDataSource @Inject constructor(
    private val repoApi: RepoApi,
    private val mapper: ApiMapper
) {

    suspend fun get(pageIndex: Int, showPerPage: Int): List<Repo> {
        val response = repoApi.getRepos(pageIndex, showPerPage)
        return response.map(mapper::toDomain)
    }
}