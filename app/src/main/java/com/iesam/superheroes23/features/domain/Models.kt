package com.iesam.superheroes23.features.domain

data class SuperHero(val id: Int, val name: String, val urlImages: List<String>) {
    fun getUrlImageS(): String = urlImages[0]
    fun getUrlImageM(): String = urlImages[1]
    fun getUrlImageL(): String = urlImages[2]
    fun getUrlImageXL(): String = urlImages[3]
}

data class Biography(val realName: String, val alignment: String)
data class Work(val occupation: String)
data class Connections(val groupAffiliation: String)
data class PowerStats(val intelligence: String, val speed: String, val combat: String)
