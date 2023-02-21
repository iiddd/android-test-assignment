package com.iiddd.abnamrorepos.data.datasource.database

import com.iiddd.abnamrorepos.data.dataSource.database.entity.RepoEntity
import com.iiddd.abnamrorepos.domain.entity.Repo

object TestUtils {

    fun prepareEntity(id: Int = 1): RepoEntity {
        return RepoEntity(
            id = id,
            name = "name1",
            fullName = "fullName1",
            description = "description1",
            ownersImage = "ownersImage",
            visibility = "public",
            isPrivate = false,
            htmlUrl = "htmlUrl1"
        )
    }

    fun prepareRepo(id: Int=1): Repo {
        return Repo(id = id,
            name = "name1",
            fullName = "fullName1",
            description = "description1",
            imageUrl = "ownersImage",
            visibility = "public",
            isPrivate = false,
            htmlUrl = "htmlUrl1")
    }
}