package com.azatberdimyradov.sportsnews.domain.repository

import com.azatberdimyradov.sportsnews.domain.model.NewsResponse

interface NewsRepository {

    suspend fun getNews(page: Int): NewsResponse

}