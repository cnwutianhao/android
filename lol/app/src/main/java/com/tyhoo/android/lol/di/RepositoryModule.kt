package com.tyhoo.android.lol.di

import com.tyhoo.android.lol.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideHeroesRepository(service: ApiService): HeroesRepository {
        return HeroesRepositoryImpl(service)
    }

    @Singleton
    @Provides
    fun provideItemsRepository(service: ApiService): ItemsRepository {
        return ItemsRepositoryImpl(service)
    }
}