package com.iesam.superheroes23.features.domain

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp

interface PowerstatsRepository {

    suspend fun getPowerstats(heroId:Int):Either<ErrorApp,PowerStats>
}