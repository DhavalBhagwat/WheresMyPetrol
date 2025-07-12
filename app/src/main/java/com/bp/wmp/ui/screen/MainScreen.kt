import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.bp.wmp.R
import com.bp.wmp.ui.activities.main.MainViewModel
import com.bp.wmp.ui.components.FilterBar
import com.bp.wmp.ui.screen.StationListScreen
import com.bp.wmp.ui.screen.StationMapScreen
import com.bp.wmp.utils.NUM_0
import com.bp.wmp.utils.NUM_1

/**
 * MainScreen is a composable function that serves as the main entry point
 * for the application. It displays a bottom navigation bar with tabs
 * for navigating between different screens (list and map view of stations).
 * It also includes a filter bar for refining the displayed stations based on various criteria.
 *
 * @param viewModel [MainViewModel] instance to manage the state and actions.
 * @param modifier [Modifier] to apply additional styling or layout properties.
 * @return [Composable] function that displays the main screen with navigation and filtering capabilities.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    // Define the border color for the filter bar
    val borderColor = Color(LocalContext.current.resources.getColor(R.color.bp_green_primary, LocalContext.current.theme))

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,
            ) {
                with(viewModel) {
                    tabs.forEachIndexed { index, value ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            icon = { Icon(value, contentDescription = null) },
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            FilterBar(
                filter = viewModel.filter,
                onRadiusSelected = { viewModel.updateRadius(it) },
                onToggleFilter = { viewModel.toggleFilter(it) },
                borderColor = borderColor,
            )
            when (viewModel.selectedTab) {
                NUM_0 -> StationListScreen(viewModel, modifier)
                NUM_1 -> StationMapScreen(viewModel, modifier)
            }
        }
    }
}
