package com.safagurdag.thedogapiapp.view.dog.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.safagurdag.domain.common.ResultState
import com.safagurdag.domain.usecase.thedogapi.DogSearchUiModel
import com.safagurdag.thedogapiapp.view.dog.viewmodel.DogSearchViewModel
import com.safagurdag.thedogapiapp.view.main.AppState
import safagurdag.thedogapp.R

@Composable
fun DogSearchScreen(
    appState: AppState,
    viewModel: DogSearchViewModel = hiltViewModel()
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val searchState by viewModel.searchState.collectAsState(ResultState.Loading())
        when (searchState) {
            is ResultState.Loading -> LoadingStateContent()
            is ResultState.Failure -> ErrorStateContent()
            is ResultState.Success -> SuccessStateContent(dogUiModel = (searchState as ResultState.Success<DogSearchUiModel>).value) {
                // item click
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.search()
    }

}

@Composable
fun LoadingStateContent(
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorStateContent(
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.i_error_cloud),
            contentDescription = "error-image",
            modifier = Modifier.size(96.dp)
        )

        Text(
            text = stringResource(R.string.generic_error),
            style = MaterialTheme.typography.subtitle1
        )
    }
}


@Composable
private fun SuccessStateContent(
    dogUiModel: DogSearchUiModel,
    onItemClicked: (DogSearchUiModel.Dog) -> Unit
) {

    val minWidth = LocalConfiguration.current.screenWidthDp * 0.44

    LazyVerticalGrid(
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Adaptive(minSize = minWidth.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        items(
            items = dogUiModel.dogList,
            key = { x -> x.id }
        ) { item ->
            DogListItem(
                dog = item,
                onItemClick = onItemClicked
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DogListItem(
    dog: DogSearchUiModel.Dog,
    onItemClick: (DogSearchUiModel.Dog) -> Unit
) {

    val thumbUrl = dog.imageUrl ?: ""

    Card(
        modifier = Modifier.requiredHeightIn(min = 280.dp),
        shape = CutCornerShape(bottomEnd = 12.dp),
        onClick = { onItemClick(dog) }
    ) {
        Column(
            Modifier.fillMaxSize(),
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp),
                model = thumbUrl,
                contentDescription = "",
                placeholder = painterResource(R.drawable.image_404_portrait),
                error = painterResource(R.drawable.image_404_portrait),
                contentScale = ContentScale.FillBounds
            )

            Divider(
                color = MaterialTheme.colors.primary,
                thickness = 4.dp
            )

            Column(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        start = 12.dp,
                        end = 8.dp,
                    ),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(
                    modifier = Modifier,
                    text = dog.name.uppercase(),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier,
                    text = dog.bred_group?.uppercase() ?: "",
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
    }
}
