package com.iesam.superheroes23.features.data.work.local.xml

import android.content.Context
import com.google.gson.Gson
import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.biograpphy.local.BiographyLocalDataRepository
import com.iesam.superheroes23.features.data.work.local.WorkLocalDataRepository
import com.iesam.superheroes23.features.domain.Biography
import com.iesam.superheroes23.features.domain.Work

class XmlWorkLocalDataRepository(
    private val context: Context
) : WorkLocalDataRepository {

    private val sharedPreferences = context.getSharedPreferences("SuperHeroes", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()
    override suspend fun saveWork(work: Work): Either<ErrorApp, Boolean> {
        try {
            val jsonWork = gson.toJson(work, Work::class.java)
            editor.putString(work.occupation,jsonWork)
            editor.apply()
        }catch (ex: Exception){
            return ErrorApp.DataError.left()
        }
        return true.right()
    }


}