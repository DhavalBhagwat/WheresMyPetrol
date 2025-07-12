package com.bp.wmp.ui.activities.main

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bp.wmp.api.MockStationApiService
import com.bp.wmp.api.StationApiService
import com.bp.wmp.domain.model.FilterCriteria
import com.bp.wmp.domain.model.Station
import com.bp.wmp.domain.usecase.GetFilteredStationsUseCase
import com.bp.wmp.repository.MockStationRepository
import com.bp.wmp.repository.StationRepository
import com.bp.wmp.utils.KEY_24_HOURS
import com.bp.wmp.utils.KEY_FUEL_CARD
import com.bp.wmp.utils.KEY_HOT_FOOD
import com.bp.wmp.utils.KEY_TOGGLE_STORE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [ViewModel] for managing the state of fuel stations and user filters.
 * This [ViewModel] interacts with the use case to fetch filtered stations
 * based on the user's current location and selected filter criteria.
 */
class MainViewModel : ViewModel() {
    /** Mock implementation of [StationApiService] to fetch stations. */
    private val apiService: StationApiService = MockStationApiService()

    /** Mock implementation of [StationRepository] to fetch stations. */
    private val repository: StationRepository = MockStationRepository(apiService)

    /** Use case for retrieving filtered stations based on user criteria. */
    private val useCase = GetFilteredStationsUseCase(repository)

    /** Current latitude and longitude of the user. */
    val currentLat = 51.5074

    /** Current longitude of the user. */
    val currentLng = -0.1278

    /** Filter criteria for fetching stations. */
    internal var filter by mutableStateOf(FilterCriteria(radiusMiles = 5.0))
        private set

    /** List of stations fetched based on the current filter criteria. */
    internal var stations by mutableStateOf<List<Station>>(emptyList())
        private set

    /** Selected tab index for the UI, used to switch between different views. */
    internal var selectedTab by mutableIntStateOf(0)

    /** Icons for the tabs in the UI. */
    internal val tabs = listOf(Icons.AutoMirrored.Default.List, Icons.Filled.Place)

    init {
        applyFilters()
    }

    /**
     * Updates the radius for the filter criteria.
     * This method updates the filter state and re-applies the filters
     * to fetch the updated list of stations.
     *
     * @param newRadius [Double] the new radius in miles to set for the filter.
     */
    internal fun updateRadius(newRadius: Double) {
        filter = filter.copy(radiusMiles = newRadius)
        applyFilters()
    }

    /**
     * Toggles the filter criteria based on the provided key.
     * This method updates the filter state and re-applies the filters
     * to fetch the updated list of stations.
     *
     * @param key [String] the key representing the filter to toggle.
     */
    internal fun toggleFilter(key: String) {
        Log.i("DHAVAL", "Toggling filter: $key")
        filter =
            when (key) {
                KEY_24_HOURS -> filter.copy(isOpen24Hours = !filter.isOpen24Hours)
                KEY_HOT_FOOD -> filter.copy(isOpen24Hours = !filter.hasHotFood)
                KEY_TOGGLE_STORE -> filter.copy(hasStore = !filter.hasStore)
                KEY_FUEL_CARD -> filter.copy(acceptsBpFuelCard = !filter.acceptsBpFuelCard)
                else -> filter
            }
        applyFilters()
    }

    /**
     * Applies the current filter criteria to fetch the list of stations.
     * This method uses the use case to execute the filtering logic
     * based on the user's current location and selected filters.
     */
    private fun applyFilters() {
        CoroutineScope(Dispatchers.IO).launch {
            stations = useCase.execute(currentLat, currentLng, filter)
        }
    }
}
