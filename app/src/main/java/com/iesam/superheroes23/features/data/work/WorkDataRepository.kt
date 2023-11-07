package com.iesam.superheroes23.features.data.work

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.data.work.remote.WorkRemoteDataRepository
import com.iesam.superheroes23.features.domain.Work
import com.iesam.superheroes23.features.domain.WorkRepository

class WorkDataRepository(
    private val remoteSource:WorkRemoteDataRepository
) : WorkRepository {
    override suspend fun getWorkByHeroId(heroId: Int): Either<ErrorApp, Work?> {
        return remoteSource.getWork(heroId)
    }
}