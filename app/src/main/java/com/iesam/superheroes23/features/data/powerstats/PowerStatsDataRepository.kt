package com.iesam.superhero.data.powerstats

import com.iesam.superhero.data.powerstats.local.PowerStatsLocalDataSource
import com.iesam.superhero.data.powerstats.remote.PowerStatsRemoteDataSource
import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.PowerStats
import com.iesam.superheroes23.features.domain.PowerstatsRepository

class PowerStatsDataRepository(
    val localSource: PowerStatsLocalDataSource,
    val remoteSource: PowerStatsRemoteDataSource
) : PowerstatsRepository {



    override suspend fun getPowerstats(heroId: Int): Either<ErrorApp, PowerStats> {
        val stats = remoteSource.getPowerStats(heroId)
        localSource.save(heroId,stats.get())
        return stats
    }
}