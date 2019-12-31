package com.ivanalvarado.template.di.module

import com.ivanalvarado.template.database.cache.UsersLocalCache
import com.ivanalvarado.template.network.services.StackOverflowService
import com.ivanalvarado.template.repository.UsersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUsersRepository(cache: UsersLocalCache, service: StackOverflowService): UsersRepository {
        return UsersRepository(cache, service)
    }
}