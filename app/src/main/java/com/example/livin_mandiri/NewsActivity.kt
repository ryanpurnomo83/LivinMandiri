package com.example.livin_mandiri

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class NewsActivity : AppCompatActivity() {

    private lateinit var tvNewsTitle: TextView
    private lateinit var imgTopHeadline: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // Inisialisasi view
        tvNewsTitle = findViewById(R.id.tvNewsTitle)
        imgTopHeadline = findViewById(R.id.imgTopHeadline)
        recyclerView = findViewById(R.id.tvAllNews)
        tvEmpty = findViewById(R.id.tvEmpty)

        // Logging Interceptor
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        // Retrofit setup
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(NewsInterface::class.java)

        // Panggil API top headlines untuk Indonesia
        api.getTopHeadlines("us", NewsInterface.API_KEY)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        val articles = response.body()?.articles ?: emptyList()

                        Log.d("NewsActivity", "Jumlah artikel: ${articles.size}")

                        if (articles.isNotEmpty()) {
                            tvEmpty.visibility = View.GONE

                            // Tampilkan headline utama
                            val firstArticle = articles[0]
                            tvNewsTitle.text = firstArticle.title
                            Glide.with(this@NewsActivity)
                                .load(firstArticle.urlToImage)
                                .into(imgTopHeadline)

                            // Tampilkan daftar berita lainnya
                            /*
                            recyclerView.layoutManager = LinearLayoutManager(this@NewsActivity)
                            recyclerView.adapter = NewsAdapter(articles)
                            */
                        } else {
                            tvEmpty.visibility = View.VISIBLE
                            tvEmpty.text = "Tidak ada berita yang ditemukan."
                        }
                    } else {
                        tvEmpty.visibility = View.VISIBLE
                        tvEmpty.text = "Gagal memuat berita (Kode: ${response.code()})"
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    tvEmpty.visibility = View.VISIBLE
                    tvEmpty.text = "Gagal memuat berita: ${t.localizedMessage}"
                    Log.e("NewsActivity", "Error: ${t.message}", t)
                }
            })

        api.getAllNews("indonesia", NewsInterface.API_KEY)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        val articles = response.body()?.articles ?: emptyList()

                        Log.d("NewsActivity", "Jumlah artikel: ${articles.size}")

                        if (articles.isNotEmpty()) {
                            recyclerView.layoutManager = LinearLayoutManager(this@NewsActivity)
                            recyclerView.adapter = NewsAdapter(articles)
                        }else{
                            tvEmpty.visibility = View.VISIBLE
                            tvEmpty.text = "Tidak ada berita yang ditemukan."
                        }
                    } else {
                        tvEmpty.visibility = View.VISIBLE
                        tvEmpty.text = "Gagal memuat berita (Kode: ${response.code()})"
                    }
                }
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    tvEmpty.visibility = View.VISIBLE
                    tvEmpty.text = "Gagal memuat berita: ${t.localizedMessage}"
                    Log.e("NewsActivity", "Error: ${t.message}", t)
                }
            })
    }
}
