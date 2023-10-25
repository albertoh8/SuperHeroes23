package com.iesam.superheroes23.features.domain

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp

interface SuperHeroRepository {

    suspend fun getAllHeroes():Either<ErrorApp,List<SuperHero>?>

    fun getHeroById(HeroId:Int): Either<ErrorApp,SuperHero>
}