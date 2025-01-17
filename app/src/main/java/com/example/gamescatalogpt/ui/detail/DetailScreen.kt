package com.example.gamescatalogpt.ui.detail

import com.example.gamescatalogpt.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.ui.components.GameTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: DetailViewModel = koinViewModel(),
    gameId: Int,
    isFavorite: Boolean = false
) {

    LaunchedEffect(gameId) {
        viewModel.loadGameById(gameId)
    }

    val state by viewModel.state.collectAsState()

    when (state) {
        is DetailState.Loading -> Text("Cargando juego...")
        is DetailState.Success -> GameContent(
            game = (state as DetailState.Success).game,
            navController = navController
        )

        is DetailState.Error -> Text((state as DetailState.Error).message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameContent(game: Game, navController: NavHostController) {
    Scaffold(
        topBar = {
            GameTopAppBar(
                title = game.title,
                onBack = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            val painter = if (game.gameUrl.isNotEmpty()) {
                rememberAsyncImagePainter(game.freeToGameProfileUrl)
            } else {
                painterResource(id = R.drawable.ic_launcher_foreground)
            }
            Image(
                painter = painter,
                contentDescription = "Imagen del juego",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Descripción:",
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
            Text(
                text = game.title,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
            )

        }
    }

}

@Composable
fun GameDetailSection(title: String, items: List<String>) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Text(
            text = title,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
        items.forEach { item ->
            Text(
                text = "• $item",
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    GameContent(
        game = Game(
            id = 582,
            title = "Tarisland",
            thumbnail = "https://www.freetogame.com/g/582/thumbnail.jpg",
            shortDescription = "A cross-platform MMORPG developed by Level Infinite and Published by Tencent.",
            gameUrl = "https://www.freetogame.com/open/tarisland",
            genre = "MMORPG",
            platform = "PC (Windows)",
            publisher = "Tencent",
            developer = "Level Infinite",
            releaseDate = "2024-06-22",
            freeToGameProfileUrl = "https://www.freetogame.com/tarisland"
        ),
        rememberNavController()
    )
}