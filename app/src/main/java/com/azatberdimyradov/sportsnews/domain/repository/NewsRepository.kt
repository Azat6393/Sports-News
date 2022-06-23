package com.azatberdimyradov.sportsnews.domain.repository

import androidx.paging.PagingData
import com.azatberdimyradov.sportsnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(countryCode: String): Flow<PagingData<Article>>

}