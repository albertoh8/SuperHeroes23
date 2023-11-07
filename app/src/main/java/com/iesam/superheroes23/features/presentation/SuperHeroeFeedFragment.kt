package com.iesam.superheroes23.features.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.iesam.superheroes23.R
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.extensions.hide
import com.iesam.superheroes23.app.extensions.visible
import com.iesam.superheroes23.databinding.ActivityMainBinding
import com.iesam.superheroes23.databinding.FragmentListBinding
import com.iesam.superheroes23.features.MainActiivity
import com.iesam.superheroes23.features.data.ApiClient
import com.iesam.superheroes23.features.data.biograpphy.BiographyDataRepository
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

class SuperHeroeFeedFragment : Fragment() {

    private var skeleton: Skeleton? = null
    private val viewModel: SuperHeroFeedViewModel by lazy {
        SuperHeroFeedViewModel(
            GetSuperHeroesFeedUseCase(
                SuperHeroDataRepository(
                    SuperHeroesRemoteApiSource(
                        ApiClient()
                    ),
                    XmlSuperheroLocalDataSource(requireContext())
                ),
                WorkDataRepository(
                    WorkRemoteApiDataSource(
                        ApiClient()
                    ),
                    XmlWorkLocalDataRepository(requireContext())
                ),
                BiographyDataRepository(
                    BiographyRemoteApiSource(
                        ApiClient()
                    ),
                    XmlBiographyLocalDataSource(requireContext())
                ),
            )
        )
    }
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val superHeroAdapter = SuperHeroesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getSuperHeroes()
    }

    private fun setupView() {
        binding.apply {
            superHeroesList.apply {
                adapter = superHeroAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                skeleton = applySkeleton(R.layout.view_superhero_feed, 5)
            }

        }
    }

    private fun setupObservers() {
        val observer =
            Observer<SuperHeroFeedViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton!!.showSkeleton()
                } else {
                    uiState.errorApp?.apply {
                        showError(uiState.errorApp)
                    }
                    uiState.heroes.apply {
                        skeleton!!.showOriginal()
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
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }



    private fun showError(error: ErrorApp) {
        binding.heroList.hide()
        if (error == ErrorApp.InternetError) {
            binding.layoutInternetError.errorView.visible()
        } else {
            binding.layoutCommonError.commonErrorView.visible()
        }
    }

    fun navTopDetail(id: Int) {
        val result = id
        (activity as MainActiivity).changeFragment(SuperHeroDetailFragment.newInstance())
        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
    }


}