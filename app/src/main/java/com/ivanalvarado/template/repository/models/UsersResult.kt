package com.ivanalvarado.template.repository.models

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ivanalvarado.template.database.entity.UserEntity
import com.ivanalvarado.template.network.models.NetworkError

data class UsersResult(
    val data: LiveData<PagedList<UserEntity>>,
    val networkError: LiveData<NetworkError>
)