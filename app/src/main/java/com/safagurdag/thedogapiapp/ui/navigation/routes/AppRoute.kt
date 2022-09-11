package com.safagurdag.thedogapiapp.ui.navigation.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument

/**
 * Enumerate routes to app navigation.
 * * Use with [AppNavHost] component.
 * * Simple routes defines here, but routes require more complexity
 * define as a separate sealed-class.
 *
 * @param name Route name.
 * @param arguments List of [NavArgument] pass to route.
 */
sealed class AppRoute(
    open val name: String,
    val arguments: List<NamedNavArgument> = listOf()
) {

    /**
     * App initial screen route.
     */
    object Launch : AppRoute(name = "launch")

    /**
     * Dog Search screen route.
     */
    object DogSearch : AppRoute(name = "search")
}
