package com.safagurdag.thedogapiapp.view.launch

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.safagurdag.thedogapiapp.ui.navigation.routes.AppRoute
import com.safagurdag.thedogapiapp.ui.theme.AppTheme
import com.safagurdag.thedogapiapp.view.main.AppState
import com.safagurdag.thedogapiapp.view.main.rememberAppState
import kotlinx.coroutines.delay
import safagurdag.thedogapp.R
import kotlin.time.Duration.Companion.seconds


@Composable
fun LaunchScreen(
    appState: AppState
) {

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = null
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(1.seconds)
        appState.navController.navigate(
            AppRoute.DogSearch.name
        ) {
            popUpTo(AppRoute.Launch.name) { inclusive = true }
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun LaunchScreenPrev() {
    AppTheme {
        LaunchScreen(
            appState = rememberAppState()
        )
    }
}