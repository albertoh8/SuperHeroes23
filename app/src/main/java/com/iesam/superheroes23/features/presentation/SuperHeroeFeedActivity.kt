package com.iesam.superheroes23.features.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.iesam.superheroes23.R
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.extensions.hide
import com.iesam.superheroes23.app.extensions.visible
import com.iesam.superheroes23.databinding.ActivityMainBinding
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.biograpphy.BiographyDataRepository
import com.iesam.superheroes23.features.data.biograpphy.local.BiographyLocalDataRepository
import com.iesam.superheroes23.features.data.biograpphy.local.xml.XmlBiographyLocalDataSource
import com.iesam.superheroes23.features.data.biograpphy.remote.api.BiographyRemoteApiSource
import com.iesam.superheroes23.features.data.superHero.SuperHeroDataRepository
import com.iesam.superheroes23.features.data.superHero.local.xml.XmlSuperheroLocalDataSource
import com.iesam.superheroes23.features.data.superHero.remote.api.SuperHeroesRemoteApiSource
import com.iesam.superheroes23.features.data.work.WorkDataRepository
import com.iesam.superheroes23.features.data.work.local.xml.XmlWorkLocalDataRepository
import com.iesam.superheroes23.features.data.work.remote.api.WorkRemoteApiDataSource
import com.iesam.superheroes23.features.domain.GetSuperHeroesFeedUseCase
import com.iesam.superheroes23.features.presentation.adapter.SuperHeroesAdapter

class SuperHeroeFeedActivity : AppCompatActivity() {

    private val skeleton: Skeleton by lazy {
        binding.superHeroesList.applySkeleton(R.layout.view_superhero_feed, 5)
    }




    private val viewModel : SuperHeroFeedViewModel by lazy {
        SuperHeroFeedViewModel(
            GetSuperHeroesFeedUseCase(
                SuperHeroDataRepository(
                    SuperHeroesRemoteApiSource(
                        ApiClient()
                    ),
                    XmlSuperheroLocalDataSource(this)
                ),
                WorkDataRepository(
                    WorkRemoteApiDataSource(
                        ApiClient()
                    ),
                    XmlWorkLocalDataRepository(this)
                ),
                BiographyDataRepository(
                    BiographyRemoteApiSource(
                        ApiClient()
                    ),
                    XmlBiographyLocalDataSource(this)
                ),
            )
        )
    }

    lateinit var  binding : ActivityMainBinding

    private val superHeroAdapter = SuperHeroesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupObservers()

        viewModel.getSuperHeroes()
        setupView()

    }



    private fun bindView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupObservers() {
        val observer =
            Observer<SuperHeroFeedViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton.showSkeleton()
                } else {
                    uiState.errorApp?.apply {
                        showError(uiState.errorApp)
                    }
                    uiState.heroes.apply {
                        skeleton.showOriginal()
                        superHeroAdapter.submitList(uiState.heroes)
                        superHeroAdapter.setOnClickDetail {
                            navTopDetail(it)
                        }
                        binding.filter.addTextChangedListener {
                            val superheroesFiltered = uiState.heroes.filter { superHero ->
                                superHero.name.contains(it.toString())
                            }
                            superHeroAdapter.submitList(superheroesFiltered)
                        }


                    }

                }
            }


        viewModel.uiState.observe(this,observer)
    }



    private fun setupView() {
        binding.apply {
            superHeroesList.layoutManager = LinearLayoutManager(
                this@SuperHeroeFeedActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            superHeroesList.adapter = superHeroAdapter
        }
    }

    private fun bindData(superHeroLists: List<GetSuperHeroesFeedUseCase.SuperHeroList>){
        superHeroAdapter.submitList(superHeroLists)
    }
    private fun showError(error: ErrorApp) {
        binding.heroList.hide()
        if(error == ErrorApp.InternetError){
            binding.layoutInternetError.errorView.visible()
        }else{
            binding.layoutCommonError.commonErrorView.visible()
        }
    }

    fun navTopDetail(id:Int){
        val intent = Intent(applicationContext, SuperHeroDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }



}