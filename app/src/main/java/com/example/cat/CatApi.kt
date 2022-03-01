package com.example.cat

import retrofit2.Call
import retrofit2.http.GET

interface CatImg {
    @GET("img")
    fun getCatImg() : Call<Cat>
}