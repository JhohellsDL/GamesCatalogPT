package com.example.gamescatalogpt.ui.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.gamescatalogpt.R
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.ui.components.GameTopAppBar
import com.example.gamescatalogpt.ui.theme.GamesCatalogPTTheme
import com.example.gamescatalogpt.ui.theme.OnPrimary
import com.example.gamescatalogpt.ui.theme.Typography
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
            game = (state as DetailState.Success).game.copy(isFavorite = isFavorite),
            navController = navController
        )

        is DetailState.Error -> Text((state as DetailState.Error).message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameContent(game: Game, navController: NavHostController) {
    GamesCatalogPTTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                GameTopAppBar(
                    title = game.title,
                    onBack = { navController.popBackStack() }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val painter = if (game.thumbnail.isNotEmpty()) {
                    rememberAsyncImagePainter(game.thumbnail)
                } else {
                    painterResource(id = R.drawable.thumbnail)
                }

                Image(
                    painter = painter,
                    contentDescription = "Imagen del juego",
                    modifier = Modifier
                        .fillMaxHeight(0.7f),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.8f),
                                    Color.Black.copy(alpha = 1f)
                                ),
                            )
                        )
                ) {
                    Text(
                        text = game.title,
                        style = Typography.headlineLarge,
                        color = OnPrimary,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(top = 56.dp)
                            .align(Alignment.TopCenter)
                    )
                }
                GameDetailSection(
                    game = game,
                    modifier = Modifier.align(alignment = Alignment.BottomCenter)
                )

            }
        }
    }
}

@Composable
fun GameDetailSection(game: Game, modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(
            topStart = 56.dp,
            topEnd = 56.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    ) {
        Column(modifier = Modifier) {
            CardIten(
                game = game
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp),
                ) {
                    Text(
                        text = "Fecha de lanzamiento",
                        style = Typography.bodySmall,
                        color = colorScheme.onSecondary,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                    )
                    Text(
                        text = game.releaseDate,
                        style = Typography.bodyLarge,
                        color = colorScheme.onPrimary,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .background(colorScheme.onSecondary)
                        .height(52.dp)
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp),
                ) {
                    Text(
                        text = "Genero",
                        style = Typography.bodySmall,
                        color = colorScheme.onSecondary,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                    )
                    Text(
                        text = game.genre,
                        style = Typography.bodyLarge,
                        color = colorScheme.onPrimary,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                    )
                }

            }
            Text(
                text = game.shortDescription,
                style = Typography.bodyMedium,
                color = colorScheme.onPrimary,
                modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CardIten(
    game: Game
) {
    val painter = if (game.thumbnail.isNotEmpty()) {
        rememberAsyncImagePainter(game.thumbnail)
    } else {
        painterResource(id = R.drawable.thumbnail)
    }
    GamesCatalogPTTheme {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(56.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(colorScheme.surface)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .width(112.dp)
                        .height(148.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(56.dp),
                    elevation = CardDefaults.elevatedCardElevation(6.dp),
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painter,
                        contentDescription = "Imagen del juego",
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier,
                    ) {
                        Text(
                            text = game.platform,
                            style = Typography.bodyMedium,
                            color = colorScheme.onPrimary,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .background(colorScheme.onSecondary, RoundedCornerShape(12.dp))
                                .padding(horizontal = 12.dp, vertical = 2.dp)
                        )
                        Text(
                            text = game.title,
                            style = Typography.titleLarge,
                            color = colorScheme.onPrimary,
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(top = 8.dp)
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(12.dp),
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "publisher",
                            )
                            Text(
                                text = game.publisher,
                                style = Typography.bodySmall,
                                color = colorScheme.onSecondary,
                                modifier = Modifier
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(top = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(12.dp),
                                imageVector = Icons.Default.Face,
                                contentDescription = "developer",
                            )
                            Text(
                                text = game.developer,
                                style = Typography.bodySmall,
                                color = colorScheme.onSecondary,
                                modifier = Modifier
                            )
                        }
                    }

                }
                Icon(
                    modifier = Modifier
                        .padding(24.dp)
                        .size(24.dp),
                    imageVector = if (game.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "favorites",
                    tint = if (game.isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}


@Preview(
    name = "Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF1E2025,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFE6E9E8,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun GameItemPreviewLight() {
    GamesCatalogPTTheme {
        GameDetailSection(
            modifier = Modifier,
            game = Game.gameMock
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF1E2025,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFE6E9E8,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewDetailScreen() {
    GameContent(
        game = Game.gameMock,
        rememberNavController()
    )
}