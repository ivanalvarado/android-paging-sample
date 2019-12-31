package com.ivanalvarado.template.di.module

import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module
class ConcurrencyModule {

    @Provides
    fun providesExecutor(): Executor = Executors.newSingleThreadExecutor()
}