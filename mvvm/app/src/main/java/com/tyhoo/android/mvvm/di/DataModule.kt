package com.tyhoo.android.mvvm.di

import com.tyhoo.android.mvvm.api.ApiService
import com.tyhoo.android.mvvm.data.HeroListDataProvider
import com.tyhoo.android.mvvm.data.HeroListDataProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideHeroListDataProvider(service: ApiService): HeroListDataProvider {
        return HeroListDataProviderImpl(service)
    }
}