package com.example.livin_mandiri.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.livin_mandiri.R
import com.google.android.material.button.MaterialButton

class CheckStatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = intent.getStringExtra("type")
        val layoutRes = if(type=="login"){
            R.layout.pages_activity_login
        }else if(type=="register"){
            R.layout.pages_activity_register
        }else {
            R.layout.pages_activity_login
        }
        setContentView(layoutRes)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // Ketika tombol diklik, arahkan ke pages_activity_main
        btnSubmit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // opsional: menutup activity sekarang supaya tidak bisa kembali dengan tombol back
        }
    }
}