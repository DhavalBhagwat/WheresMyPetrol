package com.bp.wmp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.bp.wmp.R
import com.bp.wmp.ui.theme.DIMEN_1_DP
import com.bp.wmp.ui.theme.DIMEN_50_PERCENT

/**
 * FilterDropdownMenu is a composable function that displays a dropdown menu
 * for selecting a search radius.
 * It includes a filter chip that shows the currently selected radius
 * and allows users to choose from predefined radius options.
 *
 * @param selectedRadius [Double] the currently selected search radius in miles.
 * @param onRadiusSelected [Callback] invoked when a new radius is selected.
 * @param borderColor [Color] the color of the border for the filter chip.
 * @return [Composable] function that displays the filter dropdown menu.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun FilterDropdownMenu(
    selectedRadius: Double,
    onRadiusSelected: (Double) -> Unit,
    borderColor: Color,
) {
    // State to manage the expanded state of the dropdown menu
    val expanded = remember { mutableStateOf(false) }

    // Predefined radius options in miles
    val options = listOf(0.5, 1.0, 5.0)

    // Get the current resources for string resources
    val resources = LocalContext.current.resources

    Box {
        FilterChip(
            selected = true,
            onClick = { expanded.value = true },
            label = { Text("Radius: $selectedRadius mi") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = resources.getString(R.string.label_select_radius),
                )
            },
            colors =
                FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color.Transparent,
                    selectedLabelColor = borderColor,
                    containerColor = Color.Transparent,
                    labelColor = borderColor,
                ),
            border =
                FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = true,
                    borderColor = borderColor,
                    borderWidth = DIMEN_1_DP,
                    selectedBorderWidth = DIMEN_1_DP,
                    selectedBorderColor = borderColor,
                ),
            shape = RoundedCornerShape(DIMEN_50_PERCENT),
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            options.forEach { radius ->
                DropdownMenuItem(
                    enabled = true,
                    text = { Text("$radius mi") },
                    onClick = {
                        expanded.value = false
                        onRadiusSelected(radius)
                    },
                )
            }
        }
    }
}
