package com.tyhoo.android.mvvm.di

import com.tyhoo.android.mvvm.api.ApiService
import com.tyhoo.android.mvvm.data.HeroDetailDataProvider
import com.tyhoo.android.mvvm.data.HeroDetailDataProviderImpl
import com.tyhoo.android.mvvm.data.HeroListDataProvider
import com.tyhoo.android.mvvm.data.HeroListDataProviderImpl
import com.tyhoo.android.mvvm.data.ItemListDataProvider
import com.tyhoo.android.mvvm.data.ItemListDataProviderImpl
import com.tyhoo.android.mvvm.data.RuneListDataProvider
import com.tyhoo.android.mvvm.data.RuneListDataProviderImpl
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

    @Singleton
    @Provides
    fun provideItemListDataProvider(service: ApiService): ItemListDataProvider {
        return ItemListDataProviderImpl(service)
    }

    @Singleton
    @Provides
    fun provideRuneListDataProvider(service: ApiService): RuneListDataProvider {
        return RuneListDataProviderImpl(service)
    }

    @Singleton
    @Provides
    fun provideHeroDetailDataProvider(): HeroDetailDataProvider {
        return HeroDetailDataProviderImpl()
    }
}