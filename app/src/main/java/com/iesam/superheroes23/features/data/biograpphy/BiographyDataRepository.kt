package com.iesam.superheroes23.features.data.biograpphy

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.biograpphy.local.BiographyLocalDataRepository
import com.iesam.superheroes23.features.data.biograpphy.remote.api.BiographyRemoteApiSource
import com.iesam.superheroes23.features.domain.Biography
import com.iesam.superheroes23.features.domain.BiographyRepository

class BiographyDataRepository(
    private val remoteSource: BiographyRemoteApiSource,
    private val localSource: BiographyLocalDataRepository
) : BiographyRepository {
    override suspend fun getBiographyByHeroId(heroId: Int): Either<ErrorApp, Biography?> {
            val biograp =  remoteSource.getBiography(heroId)
            biograp.getOrNull()?.let { localSource.saveBiography(heroId,it) }
            return biograp
    }
}