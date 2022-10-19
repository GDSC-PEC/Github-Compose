package org.yellowhatpro.githubpro_compose.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.yellowhatpro.githubpro_compose.data.MainRepository
import org.yellowhatpro.githubpro_compose.data.MainRepositoryImpl
import org.yellowhatpro.githubpro_compose.data.network.GithubApi
import org.yellowhatpro.githubpro_compose.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(githubApi: GithubApi, @ApplicationContext context: Context) : MainRepository = MainRepositoryImpl(githubApi , context )

    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
            .build()
            .create(GithubApi::class.java)
}