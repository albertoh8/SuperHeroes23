package com.iesam.superheroes23.features.data.biograpphy.remote.api

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.biograpphy.remote.BiographyRemoteDataRepository
import com.iesam.superheroes23.features.domain.Biography

class BiographyRemoteApiSource(
    private val apiClient: ApiClient
) : BiographyRemoteDataRepository {
    override suspend fun getBiography(heroId: Int): Either<ErrorApp, Biography?> {
        return if(apiClient.getBiography(heroId).isRight()){
            apiClient
                .getBiography(heroId)
                .get()
                ?.toDomain()
                .right()
        }else{
            ErrorApp.InternetError.left()
        }
    }
}
