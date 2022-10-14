package org.yellowhatpro.githubpro_compose.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.yellowhatpro.githubpro_compose.data.MainRepository
import org.yellowhatpro.githubpro_compose.data.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainRepository() : MainRepository = MainRepositoryImpl()
}