package com.example.rohith.e_commerce

import retrofit2.converter.gson.GsonConverterFactory

class Retrofit1 {
    companion object {
        val BASE_URL = "http://192.168.0.16:8080"
        fun getRetrofitInstance() :  retrofit2.Retrofit{
            val retrofitinstance = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofitinstance
        }
    }
}