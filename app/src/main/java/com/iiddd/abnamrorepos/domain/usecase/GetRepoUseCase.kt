package com.iiddd.abnamrorepos.domain.usecase

import com.iiddd.abnamrorepos.domain.repository.RepoRepository
import javax.inject.Inject

class GetRepoUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {
    operator fun invoke(repoId: Int) = repoRepository.getById(repoId)
}