package com.example.rohith.e_commerce

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.recycler_item.view.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        viewitem.setOnClickListener{
            val intent= Intent(this,ActivityItemsView::class.java)
            startActivity(intent)
        }
        additem.setOnClickListener{
            var cart: Cart = Cart()
            cart.name=item_name.text.toString()
            cart.price= quantity.text.toString().toInt()
            item_name.setText(" ")
            quantity.setText(" ")
            val retrofit = Retrofit1.getRetrofitInstance()
            val postitems = retrofit.create(CartFactory::class.java)
            postitems.postCartItems(cart).enqueue(object : Callback , retrofit2.Callback<String>
            {
                override fun onFailure(call: Call<String>?, t: Throwable?) {
                }
                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                }
            })

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
