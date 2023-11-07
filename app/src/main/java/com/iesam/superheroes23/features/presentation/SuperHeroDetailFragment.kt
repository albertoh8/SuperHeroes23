package com.iesam.superheroes23.features.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.iesam.superhero.data.connections.remote.api.ConnectionsApiRemoteDataSource
import com.iesam.superhero.data.powerstats.PowerStatsDataRepository
import com.iesam.superhero.data.powerstats.local.xml.XmlPowerStatsLocalDataSource
import com.iesam.superhero.data.powerstats.remote.api.PowerStatsApiRemoteDataSource
import com.iesam.superheroes23.app.extensions.loadUrl
import com.iesam.superheroes23.databinding.FragmentSuperheroDetailBinding
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

class SuperHeroDetailFragment : Fragment() {

    private val viewModel : SuperHeroDetailViewModel by lazy {
        SuperHeroDetailViewModel(GetSuperHeroDetailUseCase(
            SuperHeroDataRepository(
                SuperHeroesRemoteApiSource(ApiClient()),
                XmlSuperheroLocalDataSource(requireContext())),
            BiographyDataRepository(
                BiographyRemoteApiSource(ApiClient()),
                XmlBiographyLocalDataSource(requireContext())
            ),
            ConnectionsDataRepository(
                ConnectionsApiRemoteDataSource(ApiClient()),
                XmlConnectionLocalDataSource(requireContext())
            ),
            PowerStatsDataRepository(
                XmlPowerStatsLocalDataSource(requireContext()),
                PowerStatsApiRemoteDataSource(ApiClient())
            )))

    }

    private var _binding: FragmentSuperheroDetailBinding? = null
    private val binding get() = _binding!!
    private val superHeroDetailAdapter = SuperHeroDetailsAdapter()

    private var skeleton: Skeleton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperheroDetailBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            val result = bundle.getInt("bundleKey")
            viewModel.getHeroDetail(result)
        }
    }

    fun setupView(){
        binding.apply {
            listImages.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = superHeroDetailAdapter
            }
        }
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
        viewModel.uiState.observe(viewLifecycleOwner,observer)
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
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            superHeroDetailAdapter.setDataItems(hero.urlImages)
        }
    }

    companion object {
        fun newInstance() = SuperHeroDetailFragment()
    }

}