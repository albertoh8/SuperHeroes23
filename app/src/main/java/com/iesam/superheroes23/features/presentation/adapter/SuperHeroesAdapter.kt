package com.iesam.superheroes23.features.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iesam.superheroes23.R
import com.iesam.superheroes23.features.domain.GetSuperHeroesFeedUseCase
import com.iesam.superheroes23.features.presentation.SuperHeroDiffUtil

class SuperHeroesAdapter() : ListAdapter<GetSuperHeroesFeedUseCase.SuperHeroList,SuperHeroViewHolder>(SuperHeroDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_superhero_feed,parent,false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(currentList.get(position))
    }
}