package com.iiddd.abnamrorepos.data.mapper

import com.iiddd.abnamrorepos.data.dataSource.database.entity.RepoEntity
import com.iiddd.abnamrorepos.domain.entity.Repo
import javax.inject.Inject

class DatabaseMapper @Inject constructor() {

    fun toDomain(entity: RepoEntity) = Repo(
        id = entity.id,
        name = entity.name,
        fullName = entity.fullName,
        description = entity.description,
        imageUrl = entity.ownersImage,
        visibility = entity.visibility,
        isPrivate = entity.isPrivate,
        htmlUrl = entity.htmlUrl
    )

    fun fromDomain(entity: Repo): RepoEntity = RepoEntity(
        id = entity.id,
        name = entity.name,
        fullName = entity.fullName,
        description = entity.description,
        ownersImage = entity.imageUrl,
        visibility = entity.visibility,
        isPrivate = entity.isPrivate,
        htmlUrl = entity.htmlUrl
    )

    fun merge(currentEntity: RepoEntity?, newEntity: RepoEntity): RepoEntity = RepoEntity(
        id = newEntity.id,
        name = newEntity.name,
        fullName = newEntity.fullName ?: currentEntity?.fullName,
        description = newEntity.description ?: currentEntity?.description,
        ownersImage = newEntity.ownersImage ?: currentEntity?.ownersImage,
        visibility = newEntity.visibility ?: currentEntity?.visibility,
        isPrivate = newEntity.isPrivate ?: currentEntity?.isPrivate,
        htmlUrl = newEntity.htmlUrl ?: currentEntity?.htmlUrl
    )
}