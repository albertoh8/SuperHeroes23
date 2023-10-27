package com.iesam.superhero.data.connections.remote.api

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.connections.remote.ConnectionsRemoteDataSource
import com.iesam.superheroes23.features.domain.Connections

class ConnectionsApiRemoteDataSource (
    private val apiClient: ApiClient)
    : ConnectionsRemoteDataSource {

    override suspend fun getConnections(heroId: Int): Either<ErrorApp,Connections?> {
        if(apiClient.getConnections(heroId).isRight()){
            val connection = apiClient.getConnections(heroId).get()?.toDomain()

            return connection.right()
        }else{
            return ErrorApp.InternetError.left()
        }
    }
}