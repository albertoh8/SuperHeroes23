package com.iesam.superheroes23.features.data.superHero.remote

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.SuperHero

interface SuperHeroRemoteDataRepository {

    suspend fun getAllHeroes(): Either<ErrorApp,List<SuperHero>?>
}