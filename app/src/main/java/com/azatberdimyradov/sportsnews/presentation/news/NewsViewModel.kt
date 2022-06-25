package com.azatberdimyradov.sportsnews.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import com.azatberdimyradov.sportsnews.Resource
import com.azatberdimyradov.sportsnews.domain.model.NewsResponse
import com.azatberdimyradov.sportsnews.domain.use_case.GetNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNews: GetNews
) : ViewModel() {

    private val _newsFlow = MutableStateFlow<Resource<NewsResponse>>(Resource.Empty())
    val newsFlow: StateFlow<Resource<NewsResponse>> = _newsFlow

    var pageNumber = 1

    init {
        getNewsFromDatabase()
    }

    fun getNewsFromDatabase() {
        getNews(page = pageNumber).onEach {
            _newsFlow.value = it
        }.launchIn(viewModelScope)
    }
}
