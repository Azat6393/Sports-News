package com.azatberdimyradov.sportsnews.domain.repository

import androidx.paging.PagingSource
import com.azatberdimyradov.sportsnews.data.remote.NewsApi
import com.azatberdimyradov.sportsnews.domain.model.Article
import retrofit2.HttpException
import java.io.IOException

private const val BREAKING_NEWS_PAGE_INDEX = 1

class NewsPagingSource(
    private val newsAPI: NewsApi,
    private val countryCode: String
): PagingSource<Int, Article>() {
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val position = params.key ?: BREAKING_NEWS_PAGE_INDEX

        return try {
            val response = newsAPI.getNews(countryCode,position)
            val articles = response.articles
            LoadResult.Page(
                data = articles,
                prevKey = if (position == BREAKING_NEWS_PAGE_INDEX) null else position - 1,
                nextKey = if (articles.isEmpty()) null else position + 1
            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }
}