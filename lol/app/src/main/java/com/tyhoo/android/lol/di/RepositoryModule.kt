package com.tyhoo.android.lol.di

import com.tyhoo.android.lol.data.ApiService
import com.tyhoo.android.lol.data.HeroesRepository
import com.tyhoo.android.lol.data.HeroesRepositoryImpl
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
}