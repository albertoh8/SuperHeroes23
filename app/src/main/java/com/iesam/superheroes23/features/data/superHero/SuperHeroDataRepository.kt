package com.iesam.superheroes23.features.data.superHero

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.data.superHero.remote.api.SuperHeroesRemoteApiSource
import com.iesam.superheroes23.features.domain.SuperHero
import com.iesam.superheroes23.features.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val remoteSource: SuperHeroesRemoteApiSource
) : SuperHeroRepository {
    override suspend fun getAllHeroes(): Either<ErrorApp, List<SuperHero>?> {
        return  remoteSource.getAllHeroes()
    }

    override fun getHeroById(HeroId: Int): Either<ErrorApp, SuperHero> {
        TODO("Not yet implemented")
    }
}