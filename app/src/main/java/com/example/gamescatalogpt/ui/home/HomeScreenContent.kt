package com.example.gamescatalogpt.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.gamescatalogpt.R
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.ui.components.GameItem
import com.example.gamescatalogpt.ui.theme.DarkBackground
import com.example.gamescatalogpt.ui.theme.DarkSurface
import com.example.gamescatalogpt.ui.theme.GamesCatalogPTTheme
import com.example.gamescatalogpt.ui.theme.OnSecondary
import com.example.gamescatalogpt.ui.theme.Typography
import com.example.gamescatalogpt.ui.theme.WarningYellow

@Composable
private fun HomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                style = Typography.headlineMedium,
                color = colorScheme.onPrimary,
                modifier = Modifier,
                text = stringResource(R.string.hi)
            )
            Text(
                style = Typography.labelLarge,
                color = colorScheme.onSecondary,
                modifier = Modifier,
                text = stringResource(R.string.subtitle_home)
            )
        }
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Default.AccountCircle,
            contentDescription = stringResource(R.string.favorite_description),
            tint = colorScheme.onPrimary
        )
    }
}

@Composable
private fun CardCurrentHome(
    game: Game,
    onRefresh: () -> Unit = { }
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
                .fillMaxWidth()
                .clickable { },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(
                    modifier = Modifier
                        .matchParentSize(),
                    painter = painter,
                    contentDescription = stringResource(R.string.image_game_description),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.TopEnd),
                    onClick = onRefresh ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Add to favorites",
                        tint = DarkBackground
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 1f),
                                    Color.Black.copy(alpha = 0.5f),
                                    Color.Transparent
                                ),
                                startX = 0f,
                                endX = Float.POSITIVE_INFINITY
                            )
                        )
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 24.dp, end = 48.dp, bottom = 24.dp, top = 24.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.title_card_current),
                            style = Typography.titleLarge,
                            color = OnSecondary,
                            modifier = Modifier.wrapContentWidth()
                        )

                        Text(
                            text = game.title,
                            style = Typography.headlineSmall,
                            color = WarningYellow,
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(top = 16.dp)
                        )

                        Row(
                            modifier = Modifier.padding(top = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier.size(24.dp),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(WarningYellow)
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .align(Alignment.Center),
                                        imageVector = Icons.Default.AccountBox,
                                        contentDescription = stringResource(R.string.favorite_description),
                                        tint = DarkSurface
                                    )
                                }
                            }
                            Text(
                                text = game.platform,
                                style = Typography.titleMedium,
                                color = WarningYellow,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(start = 8.dp)
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            Card(
                                modifier = Modifier.size(24.dp),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(WarningYellow)
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .align(Alignment.Center),
                                        imageVector = Icons.Default.Face,
                                        contentDescription = stringResource(R.string.favorite_description),
                                        tint = DarkSurface
                                    )
                                }
                            }
                            Text(
                                text = game.developer,
                                style = Typography.titleMedium,
                                color = WarningYellow,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(start = 8.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    games: List<Game>,
    favorites: Set<Int>,
    randomGame: Game?,
    searchQuery: String,
    onFavoriteClick: (Int) -> Unit,
    navigatorClick: (Int) -> Unit,
    onSearchQueryGame: (String) -> Unit,
    onRandomClick: () -> Unit
) {
    Column {
        HomeHeader()
        CardCurrentHome(
            game = randomGame ?: Game(),
            onRefresh = { onRandomClick() }
        )
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryGame,
            placeholder = { Text("Buscar juegos...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyColumn {
            items(games.size) { index ->
                val game = games[index]
                GameItem(
                    game = game,
                    isFavorite = game.id in favorites,
                    onFavoriteToggle = {
                        onFavoriteClick(game.id)
                    },
                    onClickItem = {
                        navigatorClick(game.id)
                    }
                )
            }
        }
    }
}


@Preview(
    name = "Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF1E2025,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFE6E9E8,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun HomeHeaderPreview() {
    GamesCatalogPTTheme {
        HomeHeader()
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF1E2025,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFE6E9E8,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun CardCurrentHomePreview() {
    GamesCatalogPTTheme {
        CardCurrentHome(
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
            )
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF1E2025,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFE6E9E8,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF1E2025,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFE6E9E8,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun HomeScreenPreview() {
    GamesCatalogPTTheme {
        HomeScreenContent(
            games = listOf(
                Game(
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
                Game(
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
                Game(
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
                )
            ),
            randomGame = Game(
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
            favorites = setOf(1, 3),
            onFavoriteClick = { },
            navigatorClick = { },
            onRandomClick = { },
            onSearchQueryGame = { },
            searchQuery = ""
        )
    }
}