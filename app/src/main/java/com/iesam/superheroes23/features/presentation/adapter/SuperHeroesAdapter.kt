package com.iesam.superheroes23.features.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iesam.superheroes23.R
import com.iesam.superheroes23.features.domain.GetSuperHeroesFeedUseCase

class SuperHeroesAdapter() : RecyclerView.Adapter<SuperHeroViewHolder>() {

    private val list : MutableList<GetSuperHeroesFeedUseCase.SuperHeroList> = mutableListOf()

    fun setList(heroes  : List<GetSuperHeroesFeedUseCase.SuperHeroList>){
        list.clear()
        list.addAll(heroes)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_superhero_feed,parent,false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(list.get(position))
    }
}