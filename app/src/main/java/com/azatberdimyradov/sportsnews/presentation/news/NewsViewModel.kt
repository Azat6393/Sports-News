package com.azatberdimyradov.sportsnews.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.azatberdimyradov.sportsnews.domain.model.Article
import com.azatberdimyradov.sportsnews.domain.use_case.GetNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNews: GetNews
) : ViewModel() {

    private val _newsFlow = MutableStateFlow<PagingData<Article>?>(null)
    val newsFlow: StateFlow<PagingData<Article>?> = _newsFlow

    init {
        getNewsFromDatabase()
    }

    private fun getNewsFromDatabase() = viewModelScope.launch {
        getNews().onEach {
            _newsFlow.value = it
        }
    }
}