package com.iesam.superhero.data.powerstats.remote.api

import com.google.gson.annotations.SerializedName

data class PowerStatsApiModel(
    @SerializedName("speed") val speed: String,
    @SerializedName("combat") val combat: String,
    @SerializedName("intelligence") val intelligence: String
)