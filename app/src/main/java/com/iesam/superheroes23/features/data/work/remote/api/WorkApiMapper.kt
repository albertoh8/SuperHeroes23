package com.iesam.superheroes23.features.data.work.remote.api

import com.iesam.superheroes23.features.domain.Work

fun WorkApiModel.toDomain() : Work{
    return Work(
        this.occupation,
    )
}