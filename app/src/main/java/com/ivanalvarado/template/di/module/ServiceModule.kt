package com.ivanalvarado.template.di.module

import com.ivanalvarado.template.network.services.StackOverflowService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ServiceModule {

    @Provides
    @Singleton
    internal fun provideStackOverflowService(retrofit: Retrofit): StackOverflowService {
        return retrofit.create(StackOverflowService::class.java)
    }
}