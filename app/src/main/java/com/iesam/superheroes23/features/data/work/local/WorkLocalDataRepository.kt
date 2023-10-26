package com.iesam.superheroes23.features.data.work.local

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.Work

interface WorkLocalDataRepository {

    suspend fun saveWork(work: Work): Either<ErrorApp,Boolean>
}