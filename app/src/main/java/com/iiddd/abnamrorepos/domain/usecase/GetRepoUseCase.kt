package com.iiddd.abnamrorepos.domain.usecase

import com.iiddd.abnamrorepos.domain.repository.RepoRepository

class GetRepoUseCase(private val repository: RepoRepository) {

    operator fun invoke(limit: Int) = repository.getAll(limit)
}