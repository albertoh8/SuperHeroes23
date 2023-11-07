package com.iesam.superheroes23.features.data.connections.remote

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.Connections

interface ConnectionsRemoteDataSource {
    suspend fun getConnections(superHeroId: Int): Either<ErrorApp,Connections?>
}