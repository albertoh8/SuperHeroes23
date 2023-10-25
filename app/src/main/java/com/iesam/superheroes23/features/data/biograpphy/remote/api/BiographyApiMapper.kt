package com.iesam.superheroes23.features.data.biograpphy.remote.api

import com.iesam.superheroes23.features.domain.Biography

fun BiographyApiModel.toDomain():Biography {
    return Biography(
        this.realName,
        this.aligment
    )
}