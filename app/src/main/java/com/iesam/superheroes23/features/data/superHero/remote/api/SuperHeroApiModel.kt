package com.iesam.superheroes23.features.data.superHero.remote.api

import com.google.gson.annotations.SerializedName

data class SuperHeroApiModel (
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("images") val images: Images)
data class Images(
    @SerializedName("xs") val xs: String,
    @SerializedName("sm") val sm: String,
    @SerializedName("md") val md: String,
    @SerializedName("lg") val lg: String)