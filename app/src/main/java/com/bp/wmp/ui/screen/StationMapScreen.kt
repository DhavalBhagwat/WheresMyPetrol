package com.bp.wmp.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.bp.wmp.ui.activities.main.MainViewModel
import com.bp.wmp.ui.theme.DIMEN_12_FLOAT
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * StationMapScreen is a composable function that displays a map with markers for fuel stations.
 * Each marker represents a station's location, and the map is centered on the user's current location.
 * The screen is designed to be responsive and utilizes the Google Maps API for rendering the map.
 *
 * @param viewModel [MainViewModel] instance to manage the state and actions.
 * @param modifier [Modifier] to apply additional styling or layout properties.
 * @return [Composable] function that displays the map with stations.
 */
@Suppress("ktlint:standard:function-naming")
@SuppressLint("DefaultLocale")
@Composable
fun StationMapScreen(
    viewModel: MainViewModel,
    modifier: Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        val mapView = rememberMapViewWithLifecycle()
        AndroidView({ mapView }, modifier = modifier.fillMaxSize()) { view ->
            view.getMapAsync { map ->
                val london = LatLng(viewModel.currentLat, viewModel.currentLng)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(london, DIMEN_12_FLOAT))
                map.clear()
                viewModel.stations.forEach { station ->
                    map.addMarker(
                        MarkerOptions()
                            .position(LatLng(station.lat, station.lng))
                            .title(station.name)
                            .snippet("${station.address}\n${String.format("%.2f mi", station.distance)}")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)),
                    )
                }
            }
        }
    }
}

/**
 * Helper function that creates and manages the lifecycle of a [MapView].
 * It ensures that the [MapView] is properly created, resumed, paused, and destroyed
 * to prevent memory leaks and ensure proper resource management.
 *
 * @param context [Context] in which the MapView is created, defaulting to the current LocalContext.
 * @return A [MapView] instance that is managed by the lifecycle of the composable.
 */
@Composable
private fun rememberMapViewWithLifecycle(context: Context = LocalContext.current): MapView {
    val mapView = remember { MapView(context) }

    DisposableEffect(mapView) {
        mapView.onCreate(null)
        mapView.onResume()

        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }

    return mapView
}
