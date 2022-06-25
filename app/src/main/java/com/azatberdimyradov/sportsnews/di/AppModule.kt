package com.azatberdimyradov.sportsnews.di

import com.azatberdimyradov.sportsnews.Constants
import com.azatberdimyradov.sportsnews.data.remote.NewsApi
import com.azatberdimyradov.sportsnews.data.repository.NewsRepositoryImpl
import com.azatberdimyradov.sportsnews.domain.repository.NewsRepository
import com.azatberdimyradov.sportsnews.domain.use_case.GetNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): NewsApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)


    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository{
        return NewsRepositoryImpl(newsApi = newsApi)
    }

    @Provides
    @Singleton
    fun provideGetNewsUseCase(repo: NewsRepository): GetNews{
        return GetNews(repo)
    }
}