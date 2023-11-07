package com.iesam.superhero.data.powerstats.remote

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.PowerStats

interface PowerStatsRemoteDataSource {
    suspend fun getPowerStats(superHeroId: Int): Either<ErrorApp,PowerStats>
}