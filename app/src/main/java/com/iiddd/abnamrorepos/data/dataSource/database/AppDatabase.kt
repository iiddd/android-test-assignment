package com.iiddd.abnamrorepos.data.dataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iiddd.abnamrorepos.data.dataSource.database.dao.RepoDao
import com.iiddd.abnamrorepos.data.dataSource.database.entity.RepoEntity

/**
 * SQLite Database for storing the logs.
 */
@Database(entities = [RepoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}
