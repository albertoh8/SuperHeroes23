package com.iesam.superheroes23.features.data.work.local

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.Biography
import com.iesam.superheroes23.features.domain.Work

interface WorkLocalDataRepository {

    suspend fun saveWork(heroId: Int, work: Work): Either<ErrorApp,Boolean>
    suspend fun  getWork(heroId:Int): Either<ErrorApp, Work>
}