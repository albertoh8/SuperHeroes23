package com.iesam.superheroes23.features.domain

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp

interface WorkRepository {

    fun getWorkByHeroId(heroId:Int):Either<ErrorApp,Work>
}