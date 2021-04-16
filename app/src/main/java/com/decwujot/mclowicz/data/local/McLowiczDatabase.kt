package com.decwujot.mclowicz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.decwujot.mclowicz.data.model.Dish

@Database(entities = [Dish::class], version = 1)
abstract class McLowiczDatabase : RoomDatabase() {

    abstract fun mcLowiczDao(): McLowiczDao
}