package com.iesam.superheroes23.features.data.work.remote.api

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.ApiService
import com.iesam.superheroes23.features.data.work.remote.WorkRemoteDataRepository
import com.iesam.superheroes23.features.domain.Work

class WorkRemoteApiDataSource(
    private val apiClient: ApiClient
) : WorkRemoteDataRepository {
    override suspend fun getWork(superHeroId: Int): Either<ErrorApp, Work?> {
        if(apiClient.getWork(superHeroId).isRight()){
            return apiClient.getWork(superHeroId).get()?.toDomain().right()

        }else{
            return ErrorApp.InternetError.left()
        }
    }

}