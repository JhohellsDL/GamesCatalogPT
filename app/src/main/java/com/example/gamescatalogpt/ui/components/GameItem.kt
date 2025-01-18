package com.example.gamescatalogpt.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.gamescatalogpt.R
import com.example.gamescatalogpt.domain.models.Game
import com.example.gamescatalogpt.ui.theme.GamesCatalogPTTheme
import com.example.gamescatalogpt.ui.theme.Typography

@Composable
fun GameItem(
    game: Game,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onClickItem: () -> Unit
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
                .clickable { onClickItem() },
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.background(colorScheme.surface),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .size(112.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(56.dp),
                    elevation = CardDefaults.elevatedCardElevation(6.dp),
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painter,
                        contentDescription = "Imagen",
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
                            text = game.title,
                            style = Typography.titleMedium,
                            color = colorScheme.onPrimary,
                            modifier = Modifier.wrapContentWidth()
                        )
                        Text(
                            text = game.shortDescription,
                            style = Typography.bodyMedium,
                            color = colorScheme.onSecondary,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
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
                                contentDescription = "Favorite",
                            )
                            Text(
                                text = game.developer,
                                style = Typography.bodySmall,
                                color = colorScheme.onSecondary,
                                modifier = Modifier
                            )
                        }
                        IconButton(onClick = onFavoriteToggle) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                                tint = if (isFavorite) Color.Red else Color.Gray
                            )
                        }
                    }

                }
            }
        }
    }
}


@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun GameItemPreviewLight() {
    GamesCatalogPTTheme(darkTheme = false) {
        GameItem(
            game = Game.gameMock,
            isFavorite = true,
            onFavoriteToggle = { },
            onClickItem = { }
        )
    }
}