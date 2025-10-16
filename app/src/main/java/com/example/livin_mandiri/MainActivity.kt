package com.example.livin_mandiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener{item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Sudah di halaman utama, tidak perlu apa-apa
                    true
                }
                R.id.nav_products -> {
                    val intent = Intent(this, ProductsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_qris -> {
                    val intent = Intent(this, QrisActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_sukha -> {
                    val intent = Intent(this, SukhaActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        val tvNews = findViewById<TextView>(R.id.tvNews)
        tvNews.setOnClickListener{
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
    }
}