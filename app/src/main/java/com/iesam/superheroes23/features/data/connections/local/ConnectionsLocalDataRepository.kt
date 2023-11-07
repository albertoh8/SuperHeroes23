package com.iesam.superheroes23.features.data.connections.local

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.Connections

interface ConnectionsLocalDataRepository {

    suspend fun saveConnection(heroId: Int, connect: Connections): Either<ErrorApp, Boolean>
    suspend fun  getConnection(heroId:Int): Either<ErrorApp, Connections?>
}