package com.example.livin_mandiri.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.livin_mandiri.R

class DebitCardSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pages_activity_debit_card_selection)

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val btnSubmit = findViewById<Button>(R.id.btnOpAccount)
        btnSubmit.setOnClickListener {
            val intent = Intent(this, TermsConditionActivity::class.java)
            startActivity(intent)
            finish() // opsional: menutup activity sekarang supaya tidak bisa kembali dengan tombol back
        }
    }
}