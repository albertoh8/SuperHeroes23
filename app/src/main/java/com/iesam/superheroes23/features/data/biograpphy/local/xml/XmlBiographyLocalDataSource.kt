package com.iesam.superheroes23.features.data.biograpphy.local.xml

import android.content.Context
import com.google.gson.Gson
import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.biograpphy.local.BiographyLocalDataRepository
import com.iesam.superheroes23.features.domain.Biography

class XmlBiographyLocalDataSource(
    private val context: Context
) : BiographyLocalDataRepository {

    private val sharedPreferences = context.getSharedPreferences("SuperHeroes", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()
    override suspend fun saveBiography(biography: Biography): Either<ErrorApp, Boolean> {
        try {
            val jsonBiography = gson.toJson(biography, Biography::class.java)
            editor.putString(biography.realName,jsonBiography)
            editor.apply()
        }catch (ex: Exception){
            return ErrorApp.DataError.left()
        }
        return true.right()
    }


}