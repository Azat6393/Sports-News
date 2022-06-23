package com.azatberdimyradov.sportsnews.domain.model

import com.azatberdimyradov.sportsnews.domain.model.Article

data class NewsResponse(
        val articles: List<Article>,
        val status: String,
        val totalResults: Int
)