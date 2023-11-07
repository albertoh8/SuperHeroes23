package com.iesam.superheroes23.features.data.biograpphy.remote

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.Biography

interface BiographyRemoteDataRepository {

    suspend fun getBiography(heroId : Int): Either<ErrorApp,Biography?>
}