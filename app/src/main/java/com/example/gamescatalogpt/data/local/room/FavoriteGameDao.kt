package com.example.gamescatalogpt.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteGameDao {

    @Query("SELECT * FROM favorite_games")
    fun getFavoriteGames(): Flow<List<FavoriteGame>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteGame(favoriteGame: FavoriteGame)

    @Delete
    suspend fun removeFavoriteGame(favoriteGame: FavoriteGame)

    @Query("SELECT * FROM favorite_games WHERE gameId = :id")
    suspend fun getFavoriteGameById(id: Int): FavoriteGame?

}