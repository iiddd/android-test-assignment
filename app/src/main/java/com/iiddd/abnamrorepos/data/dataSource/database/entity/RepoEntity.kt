package com.iiddd.abnamrorepos.data.dataSource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Repo")
data class RepoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val fullName: String?,
    val description: String?,
    val ownersImage: String?,
    val visibility: String?,
    val isPrivate: Boolean?,
    val htmlUrl: String?
)