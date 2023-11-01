package com.example.proyectofinal_jma.viewModel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyectofinal_jma.InventoryApplication
//Inicializa los viewmodel que se requieran
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            NoteEditViewModel(
                this.createSavedStateHandle()
            )
        }
        // Initializer for NoteEntryViewModel
        initializer {
            NoteEntryViewModel(inventoryApplication().container.notesRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            NoteDetailsViewModel(
                this.createSavedStateHandle()
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel()
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)