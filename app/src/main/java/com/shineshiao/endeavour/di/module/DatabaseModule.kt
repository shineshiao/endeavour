package com.shineshiao.endeavour.di.module

import com.shineshiao.endeavour.data.local.database.Database
import com.shineshiao.endeavour.data.local.database.LocalDatabaseImpl
import com.shineshiao.endeavour.data.local.database.MemoryDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by thach.nguyen on 13,10,2022
 */

@Qualifier
annotation class BindLocalDatabase

@Qualifier
annotation class BindMemoryDatabase

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDatabaseModule {
    @BindLocalDatabase
    @Singleton
    @Binds
    abstract fun bindLocalDatabase(impl: LocalDatabaseImpl): Database
}

@InstallIn(SingletonComponent::class)
@Module
abstract class MemoryDatabaseModule {
    @BindMemoryDatabase
    @ActivityScoped
    @Binds
    abstract fun bindMemoryDatabase(impl: MemoryDatabaseImpl): Database
}
