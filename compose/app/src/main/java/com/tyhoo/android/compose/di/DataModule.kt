package com.tyhoo.android.compose.di

import com.tyhoo.android.compose.api.ApiService
import com.tyhoo.android.compose.data.ArcanaListDataProvider
import com.tyhoo.android.compose.data.ArcanaListDataProviderImpl
import com.tyhoo.android.compose.data.EquipmentListDataProvider
import com.tyhoo.android.compose.data.EquipmentListDataProviderImpl
import com.tyhoo.android.compose.data.HeroDetailDataProvider
import com.tyhoo.android.compose.data.HeroDetailDataProviderImpl
import com.tyhoo.android.compose.data.HeroListDataProvider
import com.tyhoo.android.compose.data.HeroListDataProviderImpl
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
    fun provideEquipmentListDataProvider(service: ApiService): EquipmentListDataProvider {
        return EquipmentListDataProviderImpl(service)
    }

    @Singleton
    @Provides
    fun provideArcanaListDataProvider(service: ApiService): ArcanaListDataProvider {
        return ArcanaListDataProviderImpl(service)
    }

    @Singleton
    @Provides
    fun provideHeroDetailDataProvider(): HeroDetailDataProvider {
        return HeroDetailDataProviderImpl()
    }
}