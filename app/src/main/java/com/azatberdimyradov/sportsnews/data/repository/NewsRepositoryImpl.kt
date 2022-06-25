package com.azatberdimyradov.sportsnews.data.repository

import com.azatberdimyradov.sportsnews.data.remote.NewsApi
import com.azatberdimyradov.sportsnews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getNews(page: Int) = newsApi.getNews(pageNumber = page)
}