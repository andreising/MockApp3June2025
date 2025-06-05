package com.andreising.mockapp3june2025.presentation.utils

sealed interface UiState {
    object Loading : UiState
    object Success : UiState
    data class Error(val message: String) : UiState
}