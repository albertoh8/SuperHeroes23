package com.iesam.superheroes23.features.domain

import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.right

class GetSuperHeroDetailUseCase(
    private val superHeroRepository: SuperHeroRepository,
    private val biographyRepository: BiographyRepository,
    private val connectionsRepository: ConnectionsRepository,
    private val powerStatsRepository: PowerstatsRepository,
) {

    suspend fun execute(heroId: Int): Either<ErrorApp, SuperHeroDetail> {
        val superHero = superHeroRepository.getHeroById(heroId)
        val biography = biographyRepository.getBiographyByHeroId(heroId)
        val connections = connectionsRepository.getConnections(heroId)
        val powerStats = powerStatsRepository.getPowerstats(heroId)
        return SuperHeroDetail(
            superHero.get()!!.getUrlImageM(),
            superHero.get()!!.name,
            biography.get()!!.alignment,
            biography.get()!!.realName,
            connections.get()!!.groupAffiliation,
            powerStats.get().intelligence,
            powerStats.get().speed,
            powerStats.get().combat,
            superHero.get()!!.urlImages,
            ).right()
    }


    data class SuperHeroDetail(
        val mainImageUrl: String,
        val nameSuperHero: String,
        val alignment: String,
        val realName: String,
        val groupAffiliation: String,
        val intelligence: String,
        val speed: String,
        val combat: String,
        val urlImages: List<String>,
    )
}