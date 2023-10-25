package com.iesam.superheroes23.features.data.work.remote

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.Work

interface WorkRemoteDataRepository {
    suspend fun getWork(superHeroId: Int): Either<ErrorApp, Work?>
}