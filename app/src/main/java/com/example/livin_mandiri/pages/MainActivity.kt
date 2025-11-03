package com.example.livin_mandiri.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import com.example.livin_mandiri.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pages_activity_main)
        supportActionBar?.hide()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener{item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
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
    }
}