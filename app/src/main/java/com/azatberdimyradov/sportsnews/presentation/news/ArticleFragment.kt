package com.azatberdimyradov.sportsnews.presentation.news

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.azatberdimyradov.sportsnews.R
import com.azatberdimyradov.sportsnews.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment: Fragment(R.layout.fragment_article) {

    private lateinit var binding: FragmentArticleBinding
    private val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        val article = args.article
        binding.apply {
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
        }
    }
}