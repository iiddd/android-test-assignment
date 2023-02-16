package com.iiddd.abnamrorepos.data.dataSource.database.dao

import androidx.room.*
import com.iiddd.abnamrorepos.data.dataSource.database.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data access object to query the database.
 */
@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repoEntity: RepoEntity)

    @Query("SELECT * FROM Repo WHERE id == :id")
    suspend fun getById(id: Int): RepoEntity?

    @Transaction
    @Query("SELECT * FROM Repo LIMIT :limit")
    fun getAll(limit: Int): Flow<List<RepoEntity>>

    @Transaction
    @Query("SELECT * FROM Repo WHERE id == :id")
    fun getPojoById(id: Int): Flow<RepoEntity>
}
