package com.iesam.superheroes23.features.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.iesam.superhero.data.connections.remote.api.ConnectionsApiRemoteDataSource
import com.iesam.superhero.data.powerstats.PowerStatsDataRepository
import com.iesam.superhero.data.powerstats.local.xml.XmlPowerStatsLocalDataSource
import com.iesam.superhero.data.powerstats.remote.api.PowerStatsApiRemoteDataSource
import com.iesam.superheroes23.R
import com.iesam.superheroes23.app.extensions.loadUrl
import com.iesam.superheroes23.databinding.SuperheroDetailBinding
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.biograpphy.BiographyDataRepository
import com.iesam.superheroes23.features.data.biograpphy.local.xml.XmlBiographyLocalDataSource
import com.iesam.superheroes23.features.data.biograpphy.remote.api.BiographyRemoteApiSource
import com.iesam.superheroes23.features.data.connections.ConnectionsDataRepository
import com.iesam.superheroes23.features.data.connections.local.xml.XmlConnectionLocalDataSource
import com.iesam.superheroes23.features.data.superHero.SuperHeroDataRepository
import com.iesam.superheroes23.features.data.superHero.local.xml.XmlSuperheroLocalDataSource
import com.iesam.superheroes23.features.data.superHero.remote.api.SuperHeroesRemoteApiSource
import com.iesam.superheroes23.features.domain.GetSuperHeroDetailUseCase
import com.iesam.superheroes23.features.presentation.adapter.SuperHeroDetailsAdapter

class SuperHeroDetailActivity : AppCompatActivity() {

    private val viewModel : SuperHeroDetailViewModel by lazy {
        SuperHeroDetailViewModel(GetSuperHeroDetailUseCase(
            SuperHeroDataRepository(
                SuperHeroesRemoteApiSource(ApiClient()),
                XmlSuperheroLocalDataSource(this)),
            BiographyDataRepository(
                BiographyRemoteApiSource(ApiClient()),
                XmlBiographyLocalDataSource(this)
            ),
            ConnectionsDataRepository(
                ConnectionsApiRemoteDataSource(ApiClient()),
                XmlConnectionLocalDataSource(this)
            ),
            PowerStatsDataRepository(
                XmlPowerStatsLocalDataSource(this),
                PowerStatsApiRemoteDataSource(ApiClient())
            )))

    }

    lateinit var binding : SuperheroDetailBinding
    private val superHeroDetailAdapter = SuperHeroDetailsAdapter()

    private var skeleton: Skeleton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()

        setupObservers()
        viewModel.getHeroDetail(getSuperHeroId())
    }

    private fun bindView() {
        binding = SuperheroDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupObservers() {

        val observer = Observer<SuperHeroDetailViewModel.UiState>{ uiState ->
            if (uiState.isLoading) {
                skeleton?.showSkeleton()

            } else {
                skeleton?.showOriginal()
                uiState.hero?.let {
                    bindData(it)
                }
            }
        }
        viewModel.uiState.observe(this,observer)
    }

    private fun bindData(hero : GetSuperHeroDetailUseCase.SuperHeroDetail?){
        binding.apply {
            heroImg.loadUrl(hero?.urlImages!!.get(1))
            heroName.text = hero.nameSuperHero
            heroRealname.text = hero.realName
            heroAligment.text = hero.alignment
            groupAffiliation.text = hero.groupAffiliation
            labelInteligenceValue.text = hero.intelligence
            labelCombatValue.text = hero.combat
            labelSpeedValue.text = hero.speed
            listImages.adapter = superHeroDetailAdapter
            listImages.layoutManager =
                LinearLayoutManager(
                    this@SuperHeroDetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            superHeroDetailAdapter.setDataItems(hero.urlImages)
        }
    }

    private fun getSuperHeroId(): Int = intent.getIntExtra(KEY_SUPERHEROE_ID, 0)
    companion object {
        private val KEY_SUPERHEROE_ID = "key_superheroe_id"

        fun getIntent(context: Context, herId: Int): Intent {
            val intent = Intent(context, SuperHeroDetailActivity::class.java)
            intent.putExtra(KEY_SUPERHEROE_ID, herId)
            return intent
        }
    }

}