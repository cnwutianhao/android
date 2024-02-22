package com.tyhoo.android.mvvm.di

import com.tyhoo.android.mvvm.api.ApiService
import com.tyhoo.android.mvvm.data.ArcanaListDataProvider
import com.tyhoo.android.mvvm.data.ArcanaListDataProviderImpl
import com.tyhoo.android.mvvm.data.EquipmentListDataProvider
import com.tyhoo.android.mvvm.data.EquipmentListDataProviderImpl
import com.tyhoo.android.mvvm.data.HeroDetailDataProvider
import com.tyhoo.android.mvvm.data.HeroDetailDataProviderImpl
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