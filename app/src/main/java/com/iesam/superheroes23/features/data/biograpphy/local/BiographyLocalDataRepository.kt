package com.iesam.superheroes23.features.data.biograpphy.local

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.Biography

interface BiographyLocalDataRepository {

    suspend fun  saveBiography(heroId: Int, biography: Biography): Either<ErrorApp, Boolean>
    suspend fun  getBiography(heroId:Int): Either<ErrorApp, Biography?>
}