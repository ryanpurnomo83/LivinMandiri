package com.example.livin_mandiri.helpers

import com.example.livin_mandiri.model.CountryCodeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
interface UsersInterface {

    @GET("all?fields=name,idd")
    fun getAllCountries(): Call<List<CountryCodeModel>>
}