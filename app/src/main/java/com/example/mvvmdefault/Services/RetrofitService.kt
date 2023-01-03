package com.example.mvvmdefault.Services

import com.example.mvvmdefault.Models.Photo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("v2/list?page=2")
    fun getAllPhotos(): Call<List<Photo>>

    companion object {
        private val retrofitService: RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://picsum.photos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance() : RetrofitService {
            return retrofitService
        }
    }
}

// BASE_URL = https://picsum.photos/
// PATH = v2/list?page=2