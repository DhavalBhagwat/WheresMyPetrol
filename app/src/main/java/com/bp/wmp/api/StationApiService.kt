package com.bp.wmp.api

import com.bp.wmp.domain.model.FilterCriteria
import com.bp.wmp.domain.model.Station

/**
 * Interface for a Retrofit service to fetch fuel station data from a remote API.
 * This service defines the endpoints for retrieving station information.
 */
interface StationApiService {
    /**
     * Fetches a list of fuel stations from the remote API.
     * This endpoint does not require any parameters and returns a list of [Station] objects.
     * The stations are filtered based on the provided [FilterCriteria].
     *
     * @param filter [FilterCriteria] the criteria to filter the stations.
     * @return [List<Station>] a list of fuel stations.
     */
    suspend fun getStations(filter: FilterCriteria): List<Station>
}
