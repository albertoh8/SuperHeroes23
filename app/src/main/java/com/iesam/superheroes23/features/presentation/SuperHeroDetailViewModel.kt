package com.iesam.superheroes23.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.features.domain.GetSuperHeroDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroDetailViewModel(
    private val superHeroDetail: GetSuperHeroDetailUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getHeroDetail(heroId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            superHeroDetail.execute(heroId).fold(
                {responseError(it)},
                {responseSucces(it)}
            )
        }
    }
    fun responseSucces(hero: GetSuperHeroDetailUseCase.SuperHeroDetail){
        _uiState.postValue(UiState(hero =  hero))
    }
    fun responseError(error:ErrorApp){
        _uiState.postValue(UiState(error = error))
    }
    
    data class UiState(
        val error : ErrorApp? = null,
        val isLoading : Boolean = false,
        val hero : GetSuperHeroDetailUseCase.SuperHeroDetail? = null
    )
}