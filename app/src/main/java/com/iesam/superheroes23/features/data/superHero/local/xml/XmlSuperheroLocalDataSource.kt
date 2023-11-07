package com.iesam.superheroes23.features.data.superHero.local.xml

import android.content.Context
import com.google.gson.Gson
import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.superHero.local.SuperHeroLocalDataRepository
import com.iesam.superheroes23.features.data.work.local.WorkLocalDataRepository
import com.iesam.superheroes23.features.domain.SuperHero
import com.iesam.superheroes23.features.domain.Work

class XmlSuperheroLocalDataSource(
    private val context: Context
) : SuperHeroLocalDataRepository {

    private val sharedPreferences = context.getSharedPreferences("SuperHeroes", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()
    override suspend fun saveSuperHero(heros: List<SuperHero>): Either<ErrorApp, Boolean> {
        try {
            heros.forEach { superHero ->
                editor.putString(superHero.id.toString(), gson.toJson(superHero))
            }
            editor.apply()
        }catch (ex:Exception){
            return ErrorApp.DataError.left()
        }
        return true.right()
    }

    override suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>?> {
        try {
            val superHeros: MutableList<SuperHero> = mutableListOf()
            sharedPreferences.all.forEach { map ->
                superHeros.add(gson.fromJson(map.value as String, SuperHero::class.java))
            }
            return superHeros.right()
        }catch (ex:Exception){
            return ErrorApp.DataError.left()
        }


    }


}