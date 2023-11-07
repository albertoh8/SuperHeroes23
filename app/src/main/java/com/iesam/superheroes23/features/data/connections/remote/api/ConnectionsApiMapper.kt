package com.iesam.superhero.data.connections.remote.api

import com.iesam.superheroes23.features.domain.Connections

fun ConnectionsApiModel.toDomain(): Connections {
    return Connections(this.groupAffiliation)
}
