package com.bp.wmp.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.bp.wmp.R
import com.bp.wmp.domain.model.FilterCriteria
import com.bp.wmp.ui.theme.DIMEN_4_DP
import com.bp.wmp.ui.theme.DIMEN_8_DP
import com.bp.wmp.utils.KEY_24_HOURS
import com.bp.wmp.utils.KEY_FUEL_CARD
import com.bp.wmp.utils.KEY_HOT_FOOD
import com.bp.wmp.utils.KEY_TOGGLE_STORE

/**
 * FilterBar is a composable function that displays a horizontal bar
 * with filter options for fuel stations.
 * It includes a dropdown for selecting the search radius
 * and chips for toggling various filters
 * such as 24-hour service, store presence,
 * hot food availability, and fuel card acceptance.
 *
 * @param filter [FilterCriteria] current filter criteria.
 * @param onRadiusSelected [Callback] invoked when a new radius is selected.
 * @param onToggleFilter [Callback] invoked when a filter chip is toggled.
 * @param borderColor [Color] of the border for the filter chips.
 * @return [Composable] function that displays the filter bar.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun FilterBar(
    filter: FilterCriteria,
    onRadiusSelected: (Double) -> Unit,
    onToggleFilter: (String) -> Unit,
    borderColor: Color,
) {
    // Extract the radius from the filter criteria
    val radius = filter.radiusMiles

    // Get the current resources for string resources
    val resources = LocalContext.current.resources

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = DIMEN_4_DP),
    ) {
        Row(
            modifier =
                Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = DIMEN_8_DP),
            horizontalArrangement = Arrangement.spacedBy(DIMEN_8_DP),
        ) {
            FilterDropdownMenu(
                selectedRadius = radius,
                onRadiusSelected = onRadiusSelected,
                borderColor = borderColor,
            )

            FilterChipItem(
                label = resources.getString(R.string.label_24_hours),
                selected = filter.isOpen24Hours,
                onSelectedChange = { onToggleFilter(KEY_24_HOURS) },
                borderColor = borderColor,
            )
            FilterChipItem(
                label = resources.getString(R.string.label_store),
                selected = filter.hasStore,
                onSelectedChange = { onToggleFilter(KEY_TOGGLE_STORE) },
                borderColor = borderColor,
            )
            FilterChipItem(
                label = resources.getString(R.string.label_hot_food),
                selected = filter.hasHotFood,
                onSelectedChange = { onToggleFilter(KEY_HOT_FOOD) },
                borderColor = borderColor,
            )
            FilterChipItem(
                label = resources.getString(R.string.label_fuel_card),
                selected = filter.acceptsBpFuelCard,
                onSelectedChange = { onToggleFilter(KEY_FUEL_CARD) },
                borderColor = borderColor,
            )
        }
    }
}
