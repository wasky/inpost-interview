package pl.waskysoft.inpost.screen.main

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.waskysoft.inpost.counter.AppCounter

class MainViewModelTest {

    @Test
    fun counter_isInitialValueZero() {
        val mainViewModel = MainViewModel(AppCounter())
        assertEquals(0, mainViewModel.counterValue.value)
    }

    @Test
    fun counter_isIncrementedValueOne() {
        val mainViewModel = MainViewModel(AppCounter())
        mainViewModel.incrementCounter()
        assertEquals(1, mainViewModel.counterValue.value)
    }

}