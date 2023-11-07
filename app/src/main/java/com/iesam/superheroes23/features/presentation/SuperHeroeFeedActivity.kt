package com.iesam.superheroes23.features.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iesam.superheroes23.R
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.databinding.ActivityMainBinding
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.biograpphy.BiographyDataRepository
import com.iesam.superheroes23.features.data.biograpphy.remote.api.BiographyRemoteApiSource
import com.iesam.superheroes23.features.data.superHero.SuperHeroDataRepository
import com.iesam.superheroes23.features.data.superHero.remote.api.SuperHeroesRemoteApiSource
import com.iesam.superheroes23.features.data.work.WorkDataRepository
import com.iesam.superheroes23.features.data.work.remote.api.WorkRemoteApiDataSource
import com.iesam.superheroes23.features.domain.GetSuperHeroesFeedUseCase
import com.iesam.superheroes23.features.presentation.adapter.SuperHeroesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroeFeedActivity : AppCompatActivity() {

    private val viewModel : SuperHeroFeedViewModel by lazy {
        SuperHeroFeedViewModel(
            GetSuperHeroesFeedUseCase(
                SuperHeroDataRepository(
                    SuperHeroesRemoteApiSource(
                        ApiClient())),
                WorkDataRepository(
                    WorkRemoteApiDataSource(
                        ApiClient())),
                BiographyDataRepository(
                    BiographyRemoteApiSource(
                        ApiClient())),
            )
        )
    }

    lateinit var  binding : ActivityMainBinding

    private val superHeroAdapter = SuperHeroesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupView()
        setupObservers()
    }



    private fun bindView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupObservers() {
        viewModel.getSuperHeroes()
        val observer = Observer<SuperHeroFeedViewModel.UiState>{
            it.heroes?.apply {
                bindData(this)
            }
            it.errorApp?.apply {
                showError(it.errorApp)
            }
            it.isLoading?.apply {
                showLoading()
            }
        }
        viewModel.uiState.observe(this, observer)
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
        superHeroAdapter.setList(superHeroLists)
    }
    private fun showError(it: ErrorApp) {

    }
    private fun showLoading(){

    }


}