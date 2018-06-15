package com.example.rohith.e_commerce

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback

class ActivityItemsView : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_items)
        val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.0.16:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val postCart=retrofit.create(CartFactory::class.java)
        val call = postCart.getCartItems()
        call.enqueue(object : Callback, retrofit2.Callback<List<Cart>> {
            override fun onResponse(call: Call<List<Cart>>?, response: Response<List<Cart>>) {
                val cartList = response.body()
                val n= cartList!!.size
                viewManager = GridLayoutManager (this@ActivityItemsView,2)
                viewAdapter = ItemsAdapter(cartList)
                recyclerView = findViewById<RecyclerView>(R.id.recyclerlist).apply {
                        setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }
            override fun onFailure(call: Call<List<Cart>>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
