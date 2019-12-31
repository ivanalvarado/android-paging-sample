package com.ivanalvarado.template.repository

import androidx.paging.LivePagedListBuilder
import com.ivanalvarado.template.data.UsersBoundaryCallback
import com.ivanalvarado.template.database.cache.UsersLocalCache
import com.ivanalvarado.template.network.services.StackOverflowService
import com.ivanalvarado.template.repository.models.UsersResult
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val cache: UsersLocalCache,
    private val service: StackOverflowService
) {

    fun getUsers(): UsersResult {
        val dataSourceFactory = cache.getUsersPaged()
        val boundaryCallback = UsersBoundaryCallback(service, cache)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return UsersResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}