package com.iiddd.abnamrorepos.data.repository

import com.iiddd.abnamrorepos.data.dataSource.api.RemoteDataSource
import com.iiddd.abnamrorepos.data.dataSource.database.LocalDataSource
import com.iiddd.abnamrorepos.data.datasource.database.TestUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class RepoRepositoryImplTest {

    private lateinit var repoRepository: RepoRepositoryImpl
    private val localRepo = TestUtils.prepareRepo(1)
    private val remoteRepo = localRepo.copy(description = "description")

    @Before
    fun setUp() {
        val remoteDataSource = mock<RemoteDataSource> {
            onBlocking { get(any(), any()) }.doReturn(listOf(remoteRepo))
        }

        val localDataSource = mock<LocalDataSource> {
            onBlocking { get(any(), any()) }.doReturn(listOf(localRepo))
        }

        repoRepository = RepoRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `local acts as source of truth`() = runBlocking {
        //given
        //No initialization
        //when
        val firstResult = repoRepository.getById(0)
        //then
        assertThat(firstResult, `is`(localRepo))
    }
}