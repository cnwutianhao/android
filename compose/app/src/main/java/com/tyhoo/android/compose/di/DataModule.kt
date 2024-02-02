package com.tyhoo.android.compose.di

import com.tyhoo.android.compose.api.ApiService
import com.tyhoo.android.compose.data.HeroDetailDataProvider
import com.tyhoo.android.compose.data.HeroDetailDataProviderImpl
import com.tyhoo.android.compose.data.HeroListDataProvider
import com.tyhoo.android.compose.data.HeroListDataProviderImpl
import com.tyhoo.android.compose.data.ItemListDataProvider
import com.tyhoo.android.compose.data.ItemListDataProviderImpl
import com.tyhoo.android.compose.data.RuneListDataProvider
import com.tyhoo.android.compose.data.RuneListDataProviderImpl
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