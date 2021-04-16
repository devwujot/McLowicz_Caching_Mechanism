package com.decwujot.mclowicz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class Dish(
    @PrimaryKey
    val id: Int,
    val dish: String,
    val description: String,
    val ingredient: String,
    val measurement: String
)