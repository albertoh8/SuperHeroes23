package com.iesam.superheroes23.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.GetSuperHeroesFeedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroFeedViewModel(
    private val getSuperHeroesFeed: GetSuperHeroesFeedUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getSuperHeroes(){
        _uiState.postValue(UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroesFeed.execute().fold(
                {responseError(it)},
                {responseSucces(it)}
            )
        }
    }

    fun responseSucces(heroes: List<GetSuperHeroesFeedUseCase.SuperHeroList>){
        _uiState.postValue(UiState(heroes = heroes))
    }

    fun responseError(error:ErrorApp){
        _uiState.postValue(UiState(errorApp = error))
    }


    data class UiState(
        val errorApp : ErrorApp? = null,
        val isLoading : Boolean = false,
        val heroes : List<GetSuperHeroesFeedUseCase.SuperHeroList> = emptyList()
    )
}