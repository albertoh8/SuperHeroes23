package com.iesam.superheroes23.features.domain

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp

interface ConnectionsRepository {

    suspend fun getConnections(heroId:Int): Either<ErrorApp,Connections?>
}