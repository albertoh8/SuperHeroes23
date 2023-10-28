package com.iesam.superheroes23.features.data.superHero.remote.api

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.superHero.remote.SuperHeroRemoteDataRepository
import com.iesam.superheroes23.features.domain.SuperHero

class SuperHeroesRemoteApiSource(
    private val apiClient: ApiClient
) : SuperHeroRemoteDataRepository {
    override suspend fun getAllHeroes(): Either<ErrorApp, List<SuperHero>?> {
        if(apiClient.getAllHeroes().isRight()){
            val heros = apiClient.getAllHeroes().get()?.map { superHero->
                superHero.toDomain()
            }
            return heros?.subList(0,10).right()
        }else{
            return ErrorApp.InternetError.left()
        }
    }

    override suspend fun getHeroById(heroId: Int): Either<ErrorApp, SuperHero?> {
        if(apiClient.getHeroById(heroId).isRight()){
            val hero = apiClient.getHeroById(heroId).get()?.toDomain()
            return hero.right()
        }else{
            return ErrorApp.InternetError.left()
        }
    }
}
