package com.example.gamescatalogpt.data.remote

import com.example.gamescatalogpt.utils.EMPTY
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val title: String = EMPTY,
    @SerializedName("thumbnail") val thumbnail: String = EMPTY,
    @SerializedName("short_description") val shortDescription: String = EMPTY,
    @SerializedName("game_url") val gameUrl: String = EMPTY,
    @SerializedName("genre") val genre: String = EMPTY,
    @SerializedName("platform") val platform: String = EMPTY,
    @SerializedName("publisher") val publisher: String = EMPTY,
    @SerializedName("developer") val developer: String = EMPTY,
    @SerializedName("release_date") val releaseDate: String = EMPTY,
    @SerializedName("freetogame_profile_url") val freeToGameProfileUrl: String = EMPTY
)