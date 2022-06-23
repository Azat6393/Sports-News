package com.azatberdimyradov.sportsnews.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.azatberdimyradov.sportsnews.data.remote.NewsApi
import com.azatberdimyradov.sportsnews.domain.model.NewsResponse
import com.azatberdimyradov.sportsnews.domain.repository.NewsPagingSource
import com.azatberdimyradov.sportsnews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getNews(countryCode: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsApi, countryCode) }
        ).flow
}