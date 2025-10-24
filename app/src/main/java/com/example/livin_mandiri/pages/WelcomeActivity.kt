package com.example.livin_mandiri.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.livin_mandiri.R
import android.content.Intent
import com.google.android.material.button.MaterialButton

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pages_activity_welcome)


        val btnNoAccount = findViewById<MaterialButton>(R.id.btnNoAccount)
        val btnHaveAccount = findViewById<MaterialButton>(R.id.btnHaveAccount)
        btnNoAccount.setOnClickListener{
            val register = Intent(this, CheckStatusActivity::class.java)
            register.putExtra("type", "register")
            startActivity(register)
        }
        btnHaveAccount.setOnClickListener{
            val login = Intent(this, CheckStatusActivity::class.java)
            login.putExtra("type", "login")
            startActivity(login)
        }
    }
}