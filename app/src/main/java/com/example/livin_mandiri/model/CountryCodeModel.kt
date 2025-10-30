package com.example.livin_mandiri.model

import com.google.gson.annotations.SerializedName

data class CountryCodeModel(
    @SerializedName("name") val name: Name?,
    @SerializedName("idd") val idd: Idd?
)

data class Name(
    @SerializedName("common") val common: String?
)

data class Idd(
    @SerializedName("root") val root: String?,
    @SerializedName("suffixes") val suffixes: List<String>?
)