package com.iiddd.abnamrorepos.data.dataSource.api

import com.iiddd.abnamrorepos.data.mapper.ApiMapper
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val repoApi: RepoApi,
    private val mapper: ApiMapper
) {

    suspend fun get(pageIndex: Int, showPerPage: Int) = wrapCall {
        val response = repoApi.getRepos(pageIndex, showPerPage)
        response.map(mapper::toDomain)
    }

    private suspend fun <T> wrapCall(action: suspend () -> T) = try {
        action()
    } catch (httpException: HttpException) {
        Timber.d(httpException)
        null
    } catch (unknownHostException: UnknownHostException) {
        Timber.d(unknownHostException)
        null
    }
}