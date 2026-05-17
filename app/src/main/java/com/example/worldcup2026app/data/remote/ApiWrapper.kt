package com.example.worldcup2026app.data.remote

import com.google.gson.annotations.SerializedName

data class ApiWrapper<T>(
    @SerializedName("get")
    val get: String?,

    @SerializedName("results")
    val results: Int,

    @SerializedName("response")
    val response: T
)