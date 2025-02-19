package pl.waskysoft.inpost.shipment

import javax.inject.Inject

class ShipmentRemoteDataSource @Inject constructor() {

    //val shipments = mutableStateOf(listOf(Shipment("id1", "name1"), Shipment("id2", "name2")))

    suspend fun getShipments(): ShipmentApiResult {
        return (ShipmentApiResult(true, listOf(Shipment("id1", "name1"))))
    }

}

class ShipmentApiResult(val success: Boolean, val shipments: List<Shipment>)