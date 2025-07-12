package com.bp.wmp.ui.activities.main

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.bp.wmp.ui.theme.WheresMyPetrolTheme

class MainActivity : ComponentActivity() {
    /** Instance of [MainViewModel] */
    private val viewModel: MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            WheresMyPetrolTheme {
                Scaffold(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .systemBarsPadding(),
                ) { innerPadding ->
                    MainScreen(
                        viewModel,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
