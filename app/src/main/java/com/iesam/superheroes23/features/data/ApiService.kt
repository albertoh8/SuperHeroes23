package com.iesam.superheroes23.features.data

import com.iesam.superhero.data.connections.remote.api.ConnectionsApiModel
import com.iesam.superhero.data.powerstats.remote.api.PowerStatsApiModel
import com.iesam.superheroes23.features.data.biograpphy.remote.api.BiographyApiModel
import com.iesam.superheroes23.features.data.superHero.remote.api.SuperHeroApiModel
import com.iesam.superheroes23.features.data.work.remote.api.WorkApiModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("work/{heroId}.json")
    suspend fun getWork(@Path("heroId") heroId:Int) : Response<WorkApiModel>

    @GET("heroes.json")
    suspend fun getAllHeroes() : Response<List<SuperHeroApiModel>>

    @GET("biography/{heroId}.json")
    suspend fun getBiography(@Path("heroId") heroId:Int) : Response<BiographyApiModel>

    @GET("connections/{heroId}.json")
    suspend fun getConnections(@Path("heroId") heroId: Int): Response<ConnectionsApiModel>

    @GET("powerstats/{heroId}.json")
    suspend fun getPowerstats(@Path("heroId") heroId: Int): Response<PowerStatsApiModel>
}