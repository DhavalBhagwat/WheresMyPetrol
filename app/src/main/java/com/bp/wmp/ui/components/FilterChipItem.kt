package com.bp.wmp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.bp.wmp.ui.theme.DIMEN_0_12_FLOAT
import com.bp.wmp.ui.theme.DIMEN_1_DP
import com.bp.wmp.ui.theme.DIMEN_50_PERCENT

/**
 * FilterChipItem is a composable function that displays a filter chip
 * with a label and a selection state.
 * It allows users to toggle the selection state
 * and provides visual feedback based on the selection.
 *
 * @param label [String] the label to display on the chip.
 * @param selected [Boolean] indicates whether the chip is selected.
 * @param onSelectedChange [Callback] invoked when the chip is clicked to toggle selection.
 * @param borderColor [Color] the color of the chip's border when selected.
 * @return [Composable] function that displays the filter chip.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun FilterChipItem(
    label: String,
    selected: Boolean,
    onSelectedChange: () -> Unit,
    borderColor: Color,
) {
    FilterChip(
        selected = selected,
        onClick = onSelectedChange,
        label = { Text(label) },
        colors =
            FilterChipDefaults.filterChipColors(
                selectedContainerColor = borderColor.copy(alpha = DIMEN_0_12_FLOAT),
                selectedLabelColor = borderColor,
                containerColor = Color.Transparent,
                labelColor = borderColor,
            ),
        border =
            FilterChipDefaults.filterChipBorder(
                selected = selected,
                enabled = true,
                borderColor = borderColor,
                borderWidth = DIMEN_1_DP,
                selectedBorderColor = borderColor,
            ),
        shape = RoundedCornerShape(DIMEN_50_PERCENT),
    )
}
