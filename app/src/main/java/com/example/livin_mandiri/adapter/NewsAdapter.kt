package com.example.livin_mandiri.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.livin_mandiri.R
import com.example.livin_mandiri.model.Article
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


class NewsAdapter(private val articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgThumb: ImageView = itemView.findViewById(R.id.imgNewsThumb)
        val tvTitle: TextView = itemView.findViewById(R.id.tvNewsTitleItem)
        val tvSource: TextView = itemView.findViewById(R.id.tvNewsSource)
        val tvRlsDate: TextView = itemView.findViewById(R.id.tvNewsRlsDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.components_list_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.tvTitle.text = article.title
        holder.tvSource.text = article.source?.name ?: "Unknown"

        val formattedDate = article.publishedAt?.let {
            try {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                parser.timeZone = TimeZone.getTimeZone("UTC")
                val date = parser.parse(it)
                val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id"))
                formatter.format(date!!)
            } catch (e: Exception) {
                it // fallback ke string aslinya
            }
        } ?: "-"

        holder.tvRlsDate.text = formattedDate

        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.imgThumb)
    }

    override fun getItemCount(): Int = articles.size
}
