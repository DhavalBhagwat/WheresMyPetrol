package com.bp.wmp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.bp.wmp.R
import com.bp.wmp.ui.activities.main.MainViewModel
import com.bp.wmp.ui.theme.DIMEN_1_FLOAT
import com.bp.wmp.ui.theme.DIMEN_4_DP
import com.bp.wmp.ui.theme.DIMEN_64_DP
import com.bp.wmp.ui.theme.DIMEN_8_DP

/**
 * StationListScreen is a composable function that displays a list of fuel stations.
 * Each station is represented with its name, address, and distance from the user.
 * The screen is designed to be scrollable and responsive to different screen sizes.
 *
 * @param viewModel [MainViewModel] instance to manage the state and actions.
 * @param modifier [Modifier] to apply additional styling or layout properties.
 * @return [Composable] function that displays the list of stations.
 */
@Suppress("ktlint:standard:function-naming")
@SuppressLint("DefaultLocale")
@Composable
fun StationListScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(DIMEN_1_FLOAT),
        ) {
            items(viewModel.stations) { item ->
                Column {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(DIMEN_8_DP),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bp_logo),
                            contentDescription = null,
                            modifier = Modifier.size(DIMEN_64_DP),
                        )
                        Spacer(modifier = Modifier.width(DIMEN_8_DP))
                        Column(modifier = Modifier.padding(DIMEN_8_DP)) {
                            Text(text = item.name, fontWeight = FontWeight.Bold)
                            Text(text = item.address)
                            Text(text = String.format("%.2f mi", item.distance))
                        }
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = DIMEN_4_DP))
                }
            }
        }
    }
}
