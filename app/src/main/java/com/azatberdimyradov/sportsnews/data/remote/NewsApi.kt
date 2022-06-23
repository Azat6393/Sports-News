package com.azatberdimyradov.sportsnews.data.remote

import com.azatberdimyradov.sportsnews.Constants.API_KEY
import com.azatberdimyradov.sportsnews.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("category")
        category: String = "sports",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse

}