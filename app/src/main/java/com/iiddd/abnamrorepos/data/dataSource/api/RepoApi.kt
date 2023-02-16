package com.iiddd.abnamrorepos.data.dataSource.api

import com.iiddd.abnamrorepos.data.dataSource.api.entity.Repo
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {

    @GET("users/abnamrocoesd/repos")
    suspend fun getRepos(
        @Query("page") pageNumber: Int,
        @Query("per_page") showPerPage: Int
    ): List<Repo>
}