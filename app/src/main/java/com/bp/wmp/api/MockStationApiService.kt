package com.bp.wmp.api

import com.bp.wmp.domain.model.FilterCriteria
import com.bp.wmp.domain.model.Station

/**
 * Mock implementation of [StationApiService] for testing purposes.
 */
class MockStationApiService : StationApiService {
    companion object {
        /**
         * Mock data for testing purposes.
         * This list contains a set of predefined stations with various attributes.
         */
        private val MOCK_STATIONS =
            listOf(
                Station("1", "133-135 Widmore Road", "Bromley BR1 3AX", 51.406, 0.015, true, true, true, true),
                Station("2", "Uxbridge Road", "London W12 7JP", 51.508, -0.229, false, true, false, true),
                Station("3", "Waterloo Station", "London SE1 8SW", 51.5033, -0.1147, true, false, true, false),
                Station("4", "Victoria", "London SW1V 1JT", 51.4952, -0.144, true, true, true, true),
                Station("5", "11 Swandon Way", "Wandsworth SW18 1EW", 51.459, -0.189, true, true, false, true),
                Station("6", "99 Chase Side", "Southgate N14 5BU", 51.6325, -0.1289, false, false, true, true),
                Station("7", "319 Cambridge Heath Rd", "Bethnal Green E2 9LH", 51.5322, -0.0563, true, true, true, false),
                Station("8", "Hornsey Rise", "Islington N19 3SH", 51.5693, -0.1301, true, false, false, true),
                Station("9", "104A Finchley Road", "Hampstead NW3 5EY", 51.547, -0.1789, true, true, true, true),
                Station("10", "Shepherd’s Bush Green", "London W12 8PS", 51.5046, -0.2186, true, true, false, false),
                Station("11", "193 Lee High Road", "Lewisham SE13 5PQ", 51.4576, -0.0029, false, true, true, true),
                Station("12", "16 North Circular Road", "Walthamstow E4 8QE", 51.6102, -0.0172, true, true, true, true),
                Station("13", "Watford Way", "Mill Hill NW7 2ET", 51.609, -0.239, false, true, false, true),
                Station("14", "Gunnersbury Avenue", "Ealing W5 4LR", 51.4994, -0.2801, true, true, true, false),
                Station("15", "Vauxhall Bridge Road", "London SW1V 2RE", 51.4901, -0.1376, true, false, false, true),
                Station("16", "105 Northside", "Clapham Common SW4 9SH", 51.4621, -0.1477, true, true, true, true),
                Station("17", "115 Grove Vale", "East Dulwich SE22 8EN", 51.4599, -0.0733, false, true, true, false),
                Station("18", "102 The Highway", "Tower Hamlets E1W 2BU", 51.5099, -0.0591, true, true, false, true),
                Station("19", "747 Old Kent Road", "Peckham SE15 1NZ", 51.4813, -0.0661, true, false, true, true),
                Station("20", "171 Talgarth Road", "Hammersmith W6 8BJ", 51.4892, -0.2187, true, true, true, false),
            )
    }

    override suspend fun getStations(filter: FilterCriteria): List<Station> =
        MOCK_STATIONS.filter {
            (!filter.isOpen24Hours || it.isOpen24Hours) &&
                (!filter.hasStore || it.hasStore) &&
                (!filter.hasHotFood || it.hasHotFood) &&
                (!filter.acceptsBpFuelCard || it.acceptsBpFuelCard)
        }
}
