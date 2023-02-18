package com.iiddd.abnamrorepos.di

import com.iiddd.abnamrorepos.data.dataSource.api.RemoteDataSource
import com.iiddd.abnamrorepos.data.dataSource.database.LocalDataSource
import com.iiddd.abnamrorepos.data.repository.RepoRepositoryImpl
import com.iiddd.abnamrorepos.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepoRepository(
        reposRemoteDataSource: RemoteDataSource,
        reposLocalDataSource: LocalDataSource
    ): RepoRepository {
        return RepoRepositoryImpl(reposRemoteDataSource, reposLocalDataSource)
    }
}