package com.bp.wmp.repository

import com.bp.wmp.api.StationApiService
import com.bp.wmp.domain.model.FilterCriteria
import com.bp.wmp.domain.model.Station

/**
 * Mock implementation of [StationRepository] to fetch stations,
 * but it can be replaced with actual implementation.
 *
 * @property api [StationApiService] the API service to fetch stations from.
 */
class MockStationRepository(
    private val api: StationApiService,
) : StationRepository {
    override suspend fun getStations(filterCriteria: FilterCriteria): List<Station> =
        runCatching { api.getStations(filterCriteria) }.getOrDefault(emptyList())
}
