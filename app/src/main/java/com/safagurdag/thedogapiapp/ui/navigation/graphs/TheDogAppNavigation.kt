package com.safagurdag.thedogapiapp.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.safagurdag.thedogapiapp.ui.navigation.routes.AppRoute
import com.safagurdag.thedogapiapp.view.dog.screen.DogSearchScreen
import com.safagurdag.thedogapiapp.view.main.AppState


/**
 * Define composable navigation graph to search in nav-host.
 */
fun NavGraphBuilder.dogSearchNavigation(
    appState: AppState
) {

    navigation(
        startDestination = "/",
        route = AppRoute.DogSearch.name
    ) {

        // Search
        composable(
            route = "/"
        ) {
            DogSearchScreen(
                appState = appState
            )
        }
    }
}