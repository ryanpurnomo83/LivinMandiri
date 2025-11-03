package com.example.livin_mandiri.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.livin_mandiri.R
import com.google.android.material.button.MaterialButton

class CheckStatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = intent.getStringExtra("type")
        val layoutRes = if(type=="login"){
            R.layout.pages_activity_terms_condition
        }else if(type=="register"){
            R.layout.pages_activity_register
        }else {
            R.layout.pages_activity_login
        }
        setContentView(layoutRes)

        val btnBack = findViewById<ImageButton?>(R.id.btnBack)
//        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val btnSubmit = findViewById<Button?>(R.id.btnOpAccount)
        btnSubmit?.setOnClickListener {
            val intent = Intent(this, DebitCardSelectionActivity::class.java).putExtra("type", "register")
            startActivity(intent)
            finish()
        }
    }
}