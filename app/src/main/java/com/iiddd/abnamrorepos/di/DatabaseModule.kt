package com.iiddd.abnamrorepos.di

import android.content.Context
import androidx.room.Room
import com.iiddd.abnamrorepos.data.dataSource.database.AppDatabase
import com.iiddd.abnamrorepos.data.dataSource.database.dao.RepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "repos.db"
        ).build()
    }

    @Provides
    fun provideRepoDao(database: AppDatabase): RepoDao {
        return database.repoDao()
    }
}
