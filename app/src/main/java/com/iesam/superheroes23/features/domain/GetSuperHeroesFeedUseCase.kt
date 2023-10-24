package com.iesam.superheroes23.features.domain

class GetSuperHeroesFeedUseCase(
    private val hero: SuperHeroRepository,
    private val work : WorkRepository,
    private val biography: BiographyRepository
) {
    suspend fun execute() : List<SuperHeroList>{
        val heros  = hero.getAllHeroes()

        val list = heros.get().map {superHeroe ->
            val work = work.getWorkByHeroId(superHeroe.id)
            val biography = biography.getBiographyByHeroId(superHeroe.id)
            SuperHeroList(
                superHeroe.id,
                superHeroe.name,
                biography.get().realName,
                work.get().occupation,
                superHeroe.getUrlImageS()
            )
        }
        return list
    }

    data class SuperHeroList(
        val id:Int,
        val name:String,
        val fullName:String,
        val occupation:String,
        val imgSm:String
    )

}