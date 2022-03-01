package com.example.cat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CatAdapter (var dataset : List<String>) : RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgViewHellCat : ImageView = view.findViewById(R.id.imageView_itemCatHell_cat1)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_cat_hell, viewGroup, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cat = dataset[position]
        Picasso.get().load(cat).into(viewHolder.imgViewHellCat)
    }

    override fun getItemCount() = dataset.size
}