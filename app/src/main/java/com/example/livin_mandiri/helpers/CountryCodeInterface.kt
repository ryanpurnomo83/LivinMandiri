package com.example.livin_mandiri.helpers

import com.example.livin_mandiri.model.CountryCodeModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface CountryCodeInterface {
    @GET("all?fields=name,idd")
    fun getAllCountries(): Call<List<CountryCodeModel>>

    companion object{
        private const val BASE_URL = "https://restcountries.com/v3.1/"

        val instance: CountryCodeInterface by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CountryCodeInterface::class.java)
        }
    }
}