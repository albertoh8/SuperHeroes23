package com.iesam.superheroes23.features.data

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.biograpphy.remote.api.BiographyApiModel
import com.iesam.superheroes23.features.data.superHero.remote.api.SuperHeroApiModel
import com.iesam.superheroes23.features.data.work.remote.api.WorkApiModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient  {
    val baseUrl :String = "https://dam.sitehub.es/api-curso/superheroes/"

    private val apiServices: ApiService

    init {
        apiServices = buildApiEndPoints()
    }

    fun createRotrofitClient() =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun buildApiEndPoints() = createRotrofitClient().create(ApiService::class.java)

    suspend fun getAllHeroes():Either<ErrorApp,List<SuperHeroApiModel>?>{
        val heroes = apiServices.getAllHeroes()

        if(heroes.isSuccessful){
            return heroes.body().right()
        }else{
            return ErrorApp.InternetError.left()
        }

    }

    suspend fun getWork(heroId:Int):Either<ErrorApp, WorkApiModel?>{
        val work = apiServices.getWork(heroId)
         if(work.isSuccessful){
             return work.body().right()
        }else{
            return ErrorApp.InternetError.left()
        }
    }

    suspend fun getBiography(heroId:Int):Either<ErrorApp, BiographyApiModel?>{
        val biography = apiServices.getBiography(heroId)
        if(biography.isSuccessful){
            return biography.body().right()
        }else{
            return ErrorApp.InternetError.left()
        }
    }


}