package com.decwujot.mclowicz.data.local

import androidx.room.withTransaction
import com.decwujot.mclowicz.data.network.McLowiczApi
import com.decwujot.mclowicz.utility.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class McLowiczRepository @Inject constructor(
        private val api: McLowiczApi,
        private val db: McLowiczDatabase
) {

    private val mcLowiczDao = db.mcLowiczDao()

    fun getDishes() = networkBoundResource(
            query = {
                mcLowiczDao.getAllDishes()
            },
            fetch = {
                delay(2000)
                api.getDishes()
            },
            saveFetchResult = { dishes ->
                db.withTransaction {
                    mcLowiczDao.deleteAllDishes()
                    mcLowiczDao.insertDishes(dishes)
                }
            }
    )
}