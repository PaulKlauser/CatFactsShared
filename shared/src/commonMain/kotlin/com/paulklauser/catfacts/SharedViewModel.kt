package com.paulklauser.catfacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SharedViewModel : ViewModel() {

    private val service = CatFactService()

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun fetchCatFact() {
        viewModelScope.launch {
            _uiState.update { UiState.Loading }
            delay(2.seconds)
            _uiState.update { UiState.Success(service.fetchCatFact()) }
        }
    }
}

sealed interface UiState {
    data class Success(val catFact: String) : UiState
    data object Loading : UiState
    data object Initial : UiState
}