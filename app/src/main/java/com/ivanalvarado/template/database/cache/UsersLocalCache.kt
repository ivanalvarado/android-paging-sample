package com.ivanalvarado.template.database.cache

import androidx.paging.DataSource
import com.ivanalvarado.template.database.dao.UsersDao
import com.ivanalvarado.template.database.entity.UserEntity
import com.ivanalvarado.template.util.logDebug
import java.util.concurrent.Executor
import javax.inject.Inject

class UsersLocalCache @Inject constructor(
    private val usersDao: UsersDao,
    private val ioExecutor: Executor
) {

    fun insert(users: List<UserEntity>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            logDebug("Inserting ${users.size} users")
            usersDao.insert(users)
            insertFinished()
        }
    }

    fun getUsersPaged(): DataSource.Factory<Int, UserEntity> = usersDao.getUsersPaged()
}