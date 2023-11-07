package com.iesam.superhero.data.powerstats.remote.api

import com.iesam.superhero.data.connections.remote.api.toDomain
import com.iesam.superhero.data.powerstats.remote.PowerStatsRemoteDataSource
import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.work.remote.api.toDomain
import com.iesam.superheroes23.features.domain.PowerStats

class PowerStatsApiRemoteDataSource (
    val apiClient: ApiClient
) :PowerStatsRemoteDataSource {

    override suspend fun getPowerStats(heroId: Int): Either<ErrorApp, PowerStats> {
        if(apiClient.getPowerStats(heroId).isRight()){
            return apiClient.getPowerStats(heroId).get()!!.toDomain().right()

        }else{
            return ErrorApp.InternetError.left()
        }
    }
}