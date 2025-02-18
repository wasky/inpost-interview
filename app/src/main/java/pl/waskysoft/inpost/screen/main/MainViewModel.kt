package pl.waskysoft.inpost.screen.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.waskysoft.inpost.counter.AppCounter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    // application: Application,
    // savedStateHandle: SavedStateHandle,
    private val appCounter: AppCounter
) : ViewModel() {

    val counterValue get() = appCounter.value

    fun incrementCounter() {
        appCounter.incrementCounter()
    }

}