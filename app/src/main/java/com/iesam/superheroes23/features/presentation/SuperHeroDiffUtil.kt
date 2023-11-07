package com.iesam.superheroes23.features.presentation

import androidx.recyclerview.widget.DiffUtil
import com.iesam.superheroes23.features.domain.GetSuperHeroesFeedUseCase

class SuperHeroDiffUtil : DiffUtil.ItemCallback<GetSuperHeroesFeedUseCase.SuperHeroList>() {
    override fun areItemsTheSame(
        oldItem: GetSuperHeroesFeedUseCase.SuperHeroList,
        newItem: GetSuperHeroesFeedUseCase.SuperHeroList
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GetSuperHeroesFeedUseCase.SuperHeroList,
        newItem: GetSuperHeroesFeedUseCase.SuperHeroList
    ): Boolean {
        return oldItem == newItem
    }
}