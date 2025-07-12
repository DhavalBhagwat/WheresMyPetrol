package com.bp.wmp.domain.usecase

import android.util.Log
import com.bp.wmp.domain.model.FilterCriteria
import com.bp.wmp.domain.model.Station
import com.bp.wmp.repository.StationRepository
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Use case for retrieving filtered fuel stations based on user criteria.
 * This use case interacts with the StationRepository to fetch
 * stations and applies filtering based on the provided criteria.
 *
 * @property repository [StationRepository] the repository to fetch stations from.
 */
class GetFilteredStationsUseCase(
    private val repository: StationRepository,
) {
    companion object {
        private val TAG = GetFilteredStationsUseCase::class.java.simpleName

        /** Radius of the Earth in miles, used for distance calculations. */
        private const val EARTH_RADIUS_MILES = 3958.8
    }

    /**
     * Executes the use case to retrieve a list of fuel stations
     * filtered by the specified criteria.
     * This method calculates the distance from the user's current location
     * to each station using the Haversine formula,
     * and filters the stations based on the provided criteria.
     * The stations are returned sorted by distance in ascending order.
     *
     * @param currentLat [Double] the latitude of the user's current location.
     * @param currentLng [Double] the longitude of the user's current location.
     * @param filter [FilterCriteria] the criteria to filter the stations.
     * @return [List<Station>] a list of stations that match the filter criteria,
     * sorted by distance from the user's current location.
     * @throws IllegalArgumentException if the filter criteria are invalid.
     */
    suspend fun execute(
        currentLat: Double,
        currentLng: Double,
        filter: FilterCriteria,
    ): List<Station> {
        Log.d(
            TAG,
            "execute() ::: currentLat: $currentLat, currentLng: $currentLng, filter: $filter",
        )
        return repository
            .getStations(filter)
            .distinctBy { it.id }
            .mapNotNull { station ->
                val distance = haversine(currentLat, currentLng, station.lat, station.lng)
                if (distance <= filter.radiusMiles) {
                    station.apply {
                        this.distance = distance
                    }
                } else {
                    null
                }
            }.sortedBy { it.distance }
    }

    /**
     * Calculates the distance between two geographical points using the Haversine formula.
     * This method computes the great-circle distance between two points on the Earth
     * specified by their latitude and longitude in degrees.
     *
     * @param lat1 [Double] latitude of the first point.
     * @param lon1 [Double] longitude of the first point.
     * @param lat2 [Double] latitude of the second point.
     * @param lon2 [Double] longitude of the second point.
     * @return [Double] the distance between the two points in miles.
     */
    private fun haversine(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double,
    ): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a =
            sin(dLat / 2).pow(2.0) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2).pow(2.0)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS_MILES * c
    }
}
