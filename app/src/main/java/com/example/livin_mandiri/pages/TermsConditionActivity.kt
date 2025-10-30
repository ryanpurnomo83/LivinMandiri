package com.example.livin_mandiri.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.livin_mandiri.R
import com.google.android.material.button.MaterialButton

class TermsConditionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pages_activity_terms_condition)

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val btnSubmit = findViewById<MaterialButton>(R.id.btnAgOpAccount)
        btnSubmit.setOnClickListener {
            val intent = Intent(this, VerificationActivity::class.java)
            startActivity(intent)
            finish() // opsional: menutup activity sekarang supaya tidak bisa kembali dengan tombol back
        }
    }
}