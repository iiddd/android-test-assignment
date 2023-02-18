package com.iiddd.abnamrorepos.data.mapper

import com.iiddd.abnamrorepos.data.dataSource.api.entity.Repo
import javax.inject.Inject

class ApiMapper @Inject constructor() {

    fun toDomain(entity: Repo) = com.iiddd.abnamrorepos.domain.entity.Repo(
        id = entity.id,
        name = entity.name,
        fullName = entity.full_name,
        description = entity.description,
        imageUrl = entity.owner.avatar_url,
        visibility = entity.visibility,
        isPrivate = entity.private,
        htmlUrl = entity.html_url
    )
}