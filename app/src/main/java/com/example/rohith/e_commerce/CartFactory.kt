package com.example.rohith.e_commerce

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface  CartFactory {

    @GET("/viewitems")
    fun getCartItems(): Call<List<Cart>>

    @POST("/items")
    fun postCartItems(@Body cart:Cart): Call<String>

}