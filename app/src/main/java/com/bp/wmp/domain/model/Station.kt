package com.bp.wmp.domain.model

/**
 * Data class representing a fuel station.
 *
 * @property id [String] unique identifier for the station.
 * @property name [String] name of the station.
 * @property address [String] address of the station.
 * @property lat [Double] latitude of the station's location.
 * @property lng [Double] longitude of the station's location.
 * @property isOpen24Hours [Boolean] indicates if the station is open 24 hours.
 * @property hasStore [Boolean] indicates if the station has a store.
 * @property hasHotFood [Boolean] indicates if the station serves hot food.
 * @property acceptsBpFuelCard [Boolean] indicates if the station accepts BP Fuel Card.
 */
data class Station(
    val id: String,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val isOpen24Hours: Boolean,
    val hasStore: Boolean,
    val hasHotFood: Boolean,
    val acceptsBpFuelCard: Boolean,
) {
    /** Distance from the user's current location to the station in miles. */
    var distance: Double = 0.0
}
