package com.bp.wmp.domain.model

/**
 * Data class representing filter criteria for searching fuel stations.
 *
 * @property radiusMiles [Double] the search radius in miles.
 * @property isOpen24Hours [Boolean] indicates if the station should be open 24 hours.
 * @property hasStore [Boolean] indicates if the station should have a store.
 * @property hasHotFood [Boolean] indicates if the station should serve hot food.
 * @property acceptsBpFuelCard [Boolean] indicates if the station should accept BP Fuel Card.
 */
data class FilterCriteria(
    var radiusMiles: Double,
    var isOpen24Hours: Boolean = false,
    var hasStore: Boolean = false,
    var hasHotFood: Boolean = false,
    var acceptsBpFuelCard: Boolean = false,
)
