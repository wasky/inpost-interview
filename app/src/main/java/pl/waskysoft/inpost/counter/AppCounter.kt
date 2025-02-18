package pl.waskysoft.inpost.counter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AppCounter @Inject constructor() {

    private val _value = MutableStateFlow(0)
    val value = _value.asStateFlow()

    fun incrementCounter() {
        _value.value++
    }

}