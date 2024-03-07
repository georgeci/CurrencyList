package com.georgeci.purevm.sample.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import com.georgeci.purevm.sample.presentation.theme.PureVmSampleTheme
import com.georgeci.purevm.sample.root.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.websocket.Frame

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            PureVmSampleTheme {
                AppNavigation(navController = navController)
            }
        }
    }
}