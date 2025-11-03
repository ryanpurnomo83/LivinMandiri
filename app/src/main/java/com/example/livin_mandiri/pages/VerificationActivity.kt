package com.example.livin_mandiri.pages

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.livin_mandiri.R
import com.example.livin_mandiri.model.CountryCodeModel
import com.example.livin_mandiri.helpers.CountryCodeInterface
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VerificationActivity : AppCompatActivity() {

    private lateinit var inputText1a: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pages_activity_verification)

        val etBirthDate = findViewById<TextInputEditText>(R.id.etBirthDate)
        etBirthDate.setOnClickListener {
            BirthDate(etBirthDate)
        }


        inputText1a = findViewById(R.id.inputText1a)
        inputText1a.setOnClickListener {
            fetchCountryCodes()
        }

        val btnBack: ImageButton = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val btnSubmit = findViewById<Button>(R.id.btnAgOpAccount)
        btnSubmit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // opsional: menutup activity sekarang supaya tidak bisa kembali dengan tombol back
        }
    }

    private fun BirthDate(etBirthDate: TextInputEditText){
        // 🗓️ Buat DatePicker dari Material
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih tanggal lahir")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.show(supportFragmentManager, "DATE_PICKER")

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))
            val dateString = formatter.format(Date(selectedDate))
            etBirthDate.setText(dateString)
        }
    }

    private fun fetchCountryCodes() {
        val call = CountryCodeInterface.instance.getAllCountries()

        call.enqueue(object : Callback<List<CountryCodeModel>> {
            override fun onResponse(
                call: Call<List<CountryCodeModel>>,
                response: Response<List<CountryCodeModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val countryList = response.body()!!.mapNotNull { country ->
                        val name = country.name?.common ?: return@mapNotNull null
                        val root = country.idd?.root ?: ""
                        val suffix = country.idd?.suffixes?.firstOrNull() ?: ""
                        if (root.isNotEmpty()) "$name ($root$suffix)" else null
                    }.sorted()

                    Log.d("VerificationActivity", "Daftar negara: ${countryList.joinToString()}")
                    Log.d("VerificationActivity", "Total negara: ${countryList.size}")

                    showCountryPicker(countryList)
                } else {
                    Toast.makeText(
                        this@VerificationActivity,
                        "Gagal memuat data negara",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<CountryCodeModel>>, t: Throwable) {
                Toast.makeText(
                    this@VerificationActivity,
                    "Koneksi gagal: ${t.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun showCountryPicker(countries: List<String>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pilih Kode Negara")
        val items = countries.toTypedArray()

        builder.setItems(items) { dialog, which ->
            val selected = items[which]
            val code = selected.substringAfter("(").substringBefore(")")
            inputText1a.setText(code)
            dialog.dismiss()
        }

        builder.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun sendData(){

    }
}
