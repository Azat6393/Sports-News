package com.azatberdimyradov.sportsnews.presentation.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azatberdimyradov.sportsnews.databinding.ItemArticlePreviewBinding
import com.azatberdimyradov.sportsnews.domain.model.Article
import com.bumptech.glide.Glide

class NewsAdapter : PagingDataAdapter<Article, NewsAdapter.ArticleViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null){
            holder.bind(currentItem)
        }
    }


    inner class ArticleViewHolder(private val binding: ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item != null){
                        onItemClickListener?.let {
                            it(item)
                        }
                    }
                }
            }
        }

        fun bind(article: Article){
            binding.apply {
                Glide.with(itemView).load(article.urlToImage).into(ivArticleImage)
                tvSource.text = article.source.name
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt
            }
        }
    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    val differ = AsyncListDiffer(this, DiffCallBack)

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

}