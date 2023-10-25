package com.iesam.superheroes23.features.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iesam.superheroes23.app.extensions.loadUrl
import com.iesam.superheroes23.databinding.ViewSuperheroFeedBinding
import com.iesam.superheroes23.features.domain.GetSuperHeroesFeedUseCase

class SuperHeroViewHolder(val view:View): RecyclerView.ViewHolder(view) {

    lateinit var binding: ViewSuperheroFeedBinding
    fun bind(heroes : GetSuperHeroesFeedUseCase.SuperHeroList){
        binding = ViewSuperheroFeedBinding.bind(view)
        binding.apply {
            imgHeroe.loadUrl(heroes.imgSm)
            heroName.text = heroes.name
            heroRealName.text = heroes.fullName
            occupation.text = heroes.occupation
        }
    }
}