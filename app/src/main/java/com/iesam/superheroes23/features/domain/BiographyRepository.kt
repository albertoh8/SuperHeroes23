package com.iesam.superheroes23.features.domain

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp

interface BiographyRepository {

    fun getBiographyByHeroId(heroId:Int):Either<ErrorApp,Biography>
}