package com.safagurdag.thedogapiapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.safagurdag.thedogapiapp.ui.navigation.graphs.dogSearchNavigation
import com.safagurdag.thedogapiapp.ui.navigation.routes.AppRoute
import com.safagurdag.thedogapiapp.view.dog.screen.DogSearchScreen
import com.safagurdag.thedogapiapp.view.launch.LaunchScreen
import com.safagurdag.thedogapiapp.view.main.AppState


@Composable
fun AppNavigationHost(
    appState: AppState
) {

    NavHost(
        navController = appState.navController,
        startDestination = appState.startDestination.name
    )
    {

        // Launch
        composable(
            route = AppRoute.Launch.name
        ) {
            LaunchScreen(appState = appState)
        }

        // Search
        composable(
            route = AppRoute.Launch.name
        ) {
            DogSearchScreen(appState = appState)
        }

        // Dog Search Journey
        dogSearchNavigation(appState = appState)
    }
}