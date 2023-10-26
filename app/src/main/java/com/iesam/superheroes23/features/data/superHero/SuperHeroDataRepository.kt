package com.iesam.superheroes23.features.data.superHero

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.data.superHero.local.xml.XmlSuperheroLocalDataSource
import com.iesam.superheroes23.features.data.superHero.remote.api.SuperHeroesRemoteApiSource
import com.iesam.superheroes23.features.domain.SuperHero
import com.iesam.superheroes23.features.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val remoteSource: SuperHeroesRemoteApiSource,
    private val localSource:XmlSuperheroLocalDataSource
) : SuperHeroRepository {
    override suspend fun getAllHeroes(): Either<ErrorApp, List<SuperHero>?> {

            val heroes =  remoteSource.getAllHeroes()
            heroes.getOrNull()?.let { localSource.saveSuperHero(it) }
            return heroes

    }

    override fun getHeroById(HeroId: Int): Either<ErrorApp, SuperHero> {
        TODO("Not yet implemented")
    }
}