package pl.waskysoft.inpost.screen.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(application: Application, savedStateHandle: SavedStateHandle) : AndroidViewModel(application) {

    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    fun incrementCounter() {
        _counter.value++
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            // https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                val savedStateHandle = createSavedStateHandle()
                MainViewModel(application, savedStateHandle)
            }
        }

    }

}