package pl.waskysoft.inpost.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.waskysoft.inpost.shipment.ShipmentRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    // application: Application,
    // savedStateHandle: SavedStateHandle,
    //private val appCounter: AppCounter,
    private val shipmentRepository: ShipmentRepository
) : ViewModel() {

    val shipments get() = shipmentRepository.shipments
    val apiStatus get() = shipmentRepository.apiStatus

    fun refreshShipments() {
        viewModelScope.launch {
            shipmentRepository.refreshShipments()
        }
    }

    /*val counterValue get() = appCounter.value

    fun incrementCounter() {
        appCounter.incrementCounter()
    }*/

}