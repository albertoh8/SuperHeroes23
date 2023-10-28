package com.iesam.superheroes23.features.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.iesam.superheroes23.app.extensions.loadUrl
import com.iesam.superheroes23.databinding.ViewItemSuperheroDetailBinding

class SuperHeroDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(urlImage: String) {
        val binding = ViewItemSuperheroDetailBinding.bind(view)
        binding.imageSuperhero.loadUrl(urlImage)
    }
}