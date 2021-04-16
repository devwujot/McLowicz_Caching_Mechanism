package com.decwujot.mclowicz.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decwujot.mclowicz.data.model.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface McLowiczDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDishes(dishes: List<Dish>)

    @Query("SELECT * FROM dishes")
    fun getAllDishes(): Flow<List<Dish>>

    @Query("DELETE FROM dishes")
    suspend fun deleteAllDishes()
}