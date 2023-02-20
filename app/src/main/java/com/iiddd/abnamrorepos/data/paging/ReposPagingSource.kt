package com.iiddd.abnamrorepos.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iiddd.abnamrorepos.domain.entity.Repo

typealias RepoPageLoader = suspend (pageIndex: Int, pageSize: Int) -> List<Repo>

class ReposPagingSource(
    private val loader: RepoPageLoader
) : PagingSource<Int, Repo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val pageIndex = params.key ?: 1

        return try {
            val repos = loader.invoke(pageIndex, params.loadSize)
            return LoadResult.Page(
                data = repos,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = if (repos.size == params.loadSize) pageIndex + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.nextKey?.minus(1) ?: page.prevKey?.plus(1)
    }
}