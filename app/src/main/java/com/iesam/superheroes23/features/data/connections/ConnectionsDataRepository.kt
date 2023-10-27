package com.iesam.superheroes23.features.data.connections

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.connections.local.xml.XmlConnectionLocalDataSource
import com.iesam.superheroes23.features.data.connections.remote.ConnectionsRemoteDataSource
import com.iesam.superheroes23.features.data.work.local.xml.XmlWorkLocalDataRepository
import com.iesam.superheroes23.features.data.work.remote.WorkRemoteDataRepository
import com.iesam.superheroes23.features.domain.Connections
import com.iesam.superheroes23.features.domain.ConnectionsRepository
import com.iesam.superheroes23.features.domain.Work

class ConnectionsDataRepository(
    private val remoteSource: ConnectionsRemoteDataSource,
private val localSource: XmlConnectionLocalDataSource
) : ConnectionsRepository {


    override suspend fun getConnections(heroId: Int): Either<ErrorApp, Connections?> {
        val connect = remoteSource.getConnections(heroId)
        connect.getOrNull()?.let { localSource.saveConnection(heroId, it) }
        return connect
    }
}