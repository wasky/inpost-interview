package pl.waskysoft.inpost.shipment

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class ShipmentRepository @Inject constructor(private val remoteDataSource: ShipmentRemoteDataSource) {

    enum class ApiStatus { LOADING, IDLE, ERROR }

    private val _apiStatus = MutableStateFlow(ApiStatus.IDLE)
    val apiStatus = _apiStatus.asStateFlow()

    // In production code this should be retrieved from a database
    private val _shipments = MutableStateFlow(emptyList<Shipment>())
    val shipments = _shipments.asStateFlow()

    suspend fun refreshShipments() {
        _apiStatus.value = ApiStatus.LOADING
        repeat(REPEAT_COUNT) {
            val result = remoteDataSource.getShipments()
            if (result.success) {
                _apiStatus.value = ApiStatus.IDLE

                // In production code this should be stored in a database
                _shipments.value = result.shipments
                return
            }
            delay(DELAY_TIMEOUT)
        }
        _apiStatus.value = ApiStatus.ERROR
    }

    companion object {

        private const val DELAY_TIMEOUT = 2000L
        private const val REPEAT_COUNT = 3

    }

}