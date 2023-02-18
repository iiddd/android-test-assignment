package com.iiddd.abnamrorepos.domain.usecase

import com.iiddd.abnamrorepos.domain.repository.RepoRepository
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val repository: RepoRepository
) {

    operator fun invoke(limit: Int) = repository.getAll(limit)
}