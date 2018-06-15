package com.example.rohith.e_commerce

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_item.view.*

class ItemsAdapter(var items: List<Cart>): RecyclerView.Adapter<ItemsAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent?.context).inflate(R.layout.recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item= items[position]
        holder.itemname.text=item.name
        holder.price.text=item.price.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ItemHolder(view: View): RecyclerView.ViewHolder(view){
            var itemname: TextView = view.findViewById(R.id.itemname)
            var price: TextView = view.findViewById(R.id.price)
    }
}