package com.iesam.superheroes23.features.data.biograpphy

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.data.biograpphy.remote.api.BiographyRemoteApiSource
import com.iesam.superheroes23.features.domain.Biography
import com.iesam.superheroes23.features.domain.BiographyRepository

class BiographyDataRepository(
    private val remoteSource: BiographyRemoteApiSource
) : BiographyRepository {
    override suspend fun getBiographyByHeroId(heroId: Int): Either<ErrorApp, Biography?> {
        return remoteSource.getBiography(heroId)
    }
}