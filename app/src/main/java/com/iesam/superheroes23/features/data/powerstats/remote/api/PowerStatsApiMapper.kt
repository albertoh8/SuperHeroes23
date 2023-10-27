package com.iesam.superhero.data.powerstats.remote.api

import com.iesam.superheroes23.features.domain.PowerStats

fun PowerStatsApiModel.toDomain() =
    PowerStats(this.intelligence, this.speed, this.combat)

