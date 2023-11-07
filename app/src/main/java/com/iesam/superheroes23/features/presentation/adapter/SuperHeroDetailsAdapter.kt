package com.iesam.superheroes23.features.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iesam.superheroes23.R

class SuperHeroDetailsAdapter : RecyclerView.Adapter<SuperHeroDetailViewHolder>() {

    private val dataItems = mutableListOf<String>()

    fun setDataItems(superHeroesDetails: List<String>) {
        dataItems.clear()
        dataItems.addAll(superHeroesDetails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroDetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_superhero_detail, parent, false)
        return SuperHeroDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperHeroDetailViewHolder, position: Int) {
        holder.bind(dataItems[position])
    }

    override fun getItemCount(): Int = dataItems.size
}