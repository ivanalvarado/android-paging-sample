package com.ivanalvarado.template.di.module

import com.ivanalvarado.template.database.cache.UsersLocalCache
import com.ivanalvarado.template.database.dao.UsersDao
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module(includes = [DbModule::class, ConcurrencyModule::class])
class LocalCacheModule {

    @Provides
    @Singleton
    fun providesUsersLocalCache(usersDao: UsersDao, ioExecutor: Executor): UsersLocalCache {
        return UsersLocalCache(usersDao, ioExecutor)
    }
}