package com.example.proyectofinal_jma.viewModel

import androidx.lifecycle.ViewModel
import com.example.proyectofinal_jma.data.NotaEntity

/**
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel() : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<NotaEntity> = listOf())