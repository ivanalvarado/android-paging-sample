package com.ivanalvarado.template.database.cache

import androidx.paging.DataSource
import com.ivanalvarado.template.database.dao.UsersDao
import com.ivanalvarado.template.database.entity.UserEntity
import java.util.concurrent.Executor
import javax.inject.Inject

class UsersLocalCache @Inject constructor(
    private val usersDao: UsersDao,
    private val ioExecutor: Executor
) {

    fun insert(users: List<UserEntity>) {
        ioExecutor.execute {
            usersDao.insert(users)
        }
    }

    fun getUsersPaged(): DataSource.Factory<Int, UserEntity> = usersDao.getUsersPaged()
}