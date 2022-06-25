package com.azatberdimyradov.sportsnews.domain.use_case

import com.azatberdimyradov.sportsnews.Resource
import com.azatberdimyradov.sportsnews.domain.model.NewsResponse
import com.azatberdimyradov.sportsnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNews @Inject constructor(
    private val repo: NewsRepository
) {

    operator fun invoke(page: Int): Flow<Resource<NewsResponse>> = flow {
        try {
            emit(Resource.Loading<NewsResponse>())
            val response = repo.getNews(page = page)
            emit(Resource.Success<NewsResponse>(response))
        } catch (e: IOException) {
            emit(Resource.Error<NewsResponse>("Error"))
        } catch (e: HttpException) {
            emit(Resource.Error<NewsResponse>("Error"))
        }
    }
}