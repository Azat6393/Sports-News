package com.azatberdimyradov.sportsnews.domain.use_case

import com.azatberdimyradov.sportsnews.domain.repository.NewsRepository
import javax.inject.Inject

class GetNews @Inject constructor(
    private val repo: NewsRepository
) {

    suspend operator fun invoke() = repo.getNews("us")
}