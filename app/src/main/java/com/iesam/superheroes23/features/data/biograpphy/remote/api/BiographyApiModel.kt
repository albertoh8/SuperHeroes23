package com.iesam.superheroes23.features.data.biograpphy.remote.api

import com.google.gson.annotations.SerializedName

data class BiographyApiModel (
    @SerializedName("fullName")  val realName:String,
    @SerializedName("alignment")  val aligment:String
)