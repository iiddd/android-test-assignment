package com.iiddd.abnamrorepos.domain.entity

data class Repo(
    val id: Int,
    val name: String,
    val fullName: String?,
    val description: String?,
    val imageUrl: String?,
    val visibility: String?,
    val isPrivate: Boolean?,
    val htmlUrl: String
)