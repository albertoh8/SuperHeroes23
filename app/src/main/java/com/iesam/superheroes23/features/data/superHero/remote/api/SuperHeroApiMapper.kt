package com.iesam.superheroes23.features.data.superHero.remote.api

import com.iesam.superheroes23.features.domain.SuperHero

fun SuperHeroApiModel.toDomain():SuperHero {
    return SuperHero(
        this.id,
        this.name,
        listOf(
            this.images.xs, this.images.sm, this.images.md, this.images.lg
        )
    )

}