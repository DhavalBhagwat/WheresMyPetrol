package com.bp.wmp.repository

import com.bp.wmp.domain.model.FilterCriteria
import com.bp.wmp.domain.model.Station

/**
 * Interface for a repository that provides access to fuel station data.
 * This repository is responsible for fetching
 * the list of fuel stations
 * and can be implemented to retrieve data from various sources,
 * such as a local database or a remote API.
 */
interface StationRepository {
    /**
     * Fetches a list of fuel stations.
     * This method retrieves all available stations from the data source.
     *
     * @param filterCriteria [FilterCriteria] the criteria to filter the stations.
     * @return [List<Station>] a list of stations.
     */
    suspend fun getStations(filterCriteria: FilterCriteria): List<Station>
}
