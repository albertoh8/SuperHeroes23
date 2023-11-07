package com.iesam.superheroes23.features.data.superHero.local

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.GetSuperHeroesFeedUseCase
import com.iesam.superheroes23.features.domain.SuperHero

interface SuperHeroLocalDataRepository {

    suspend fun saveSuperHero(hero:List<SuperHero>):Either<ErrorApp,Boolean>
    suspend fun getSuperHeroes():Either<ErrorApp,List<SuperHero>?>
}