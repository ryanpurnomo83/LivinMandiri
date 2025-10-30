package com.example.livin_mandiri.pages

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.livin_mandiri.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class QrisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pages_activity_qris)
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
        checkCameraPermission()
    }
    private fun checkCameraPermission(){
        val permission = Manifest.permission.CAMERA
        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Jika belum diizinkan, minta izin
            ActivityCompat.requestPermissions(this, arrayOf(permission), CAMERA_REQUEST_CODE)
        } else {
            // Jika sudah diizinkan, jalankan kamera
            startCameraPreview()
        }
    }

    private fun startCameraPreview() {
        Toast.makeText(this, "Kamera siap digunakan", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Izin kamera diberikan", Toast.LENGTH_SHORT).show()
                startCameraPreview()
            } else {
                Toast.makeText(this, "Izin kamera ditolak", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 101
    }

}