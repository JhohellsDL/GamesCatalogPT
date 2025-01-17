package com.example.gamescatalogpt.data.local.room

import androidx.room.TypeConverter
import kotlin.collections.joinToString
import kotlin.text.split

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}
