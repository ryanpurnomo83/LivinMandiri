package com.example.livin_mandiri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsAdapter(private val articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgThumb: ImageView = itemView.findViewById(R.id.imgNewsThumb)
        val tvTitle: TextView = itemView.findViewById(R.id.tvNewsTitleItem)
        val tvSource: TextView = itemView.findViewById(R.id.tvNewsSource)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.tvTitle.text = article.title
        holder.tvSource.text = article.source?.name ?: "Unknown"

        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.imgThumb)
    }

    override fun getItemCount(): Int = articles.size
}
