package com.shineshiao.endeavour.di.module

import android.app.Application
import android.content.Context
import com.shineshiao.endeavour.data.DataManager
import com.shineshiao.endeavour.data.DataManagerImpl
import com.shineshiao.endeavour.data.local.database.Database
import com.shineshiao.endeavour.data.local.database.LocalDatabaseImpl
import com.shineshiao.endeavour.data.local.prefs.AppPreferences
import com.shineshiao.endeavour.data.local.prefs.AppPreferencesImpl
import com.shineshiao.endeavour.error.ErrorWrapper
import com.shineshiao.endeavour.error.impl.ErrorWrapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideErrorWrapper(impl: ErrorWrapperImpl): ErrorWrapper {
        return impl
    }

    @Provides
    fun provideDataManager(appDataManager: DataManagerImpl): DataManager {
        return appDataManager
    }

    @Singleton
    @Provides
    fun provideAppPreferences(appPreferences: AppPreferencesImpl): AppPreferences {
        return appPreferences
    }

    @Singleton
    @Provides
    fun provideLocalDatabase(localDatabase: LocalDatabaseImpl): Database {
        return localDatabase
    }
}
