package com.iesam.superheroes23.features.domain

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp

interface SuperHeroRepository {

    suspend fun getAllHeroes():Either<ErrorApp,List<SuperHero>?>

    suspend fun getHeroById(heroId:Int): Either<ErrorApp,SuperHero?>
}