package com.iesam.superhero.data.connections.remote.api

import com.google.gson.annotations.SerializedName

data class ConnectionsApiModel(@SerializedName("groupAffiliation") val groupAffiliation: String)