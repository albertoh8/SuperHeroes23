package com.iesam.superhero.data.powerstats.local

import com.iesam.superheroes23.features.domain.PowerStats

interface PowerStatsLocalDataSource {
    fun save(heroId: Int, powerStats: PowerStats)
    fun getPowerStats(heroId: Int): PowerStats?
}