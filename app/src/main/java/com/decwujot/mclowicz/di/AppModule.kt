package com.decwujot.mclowicz.di

import android.app.Application
import androidx.room.Room
import com.decwujot.mclowicz.data.local.McLowiczDatabase
import com.decwujot.mclowicz.data.network.McLowiczApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl(McLowiczApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides
    @Singleton
    fun provideMcLowiczApi(retrofit: Retrofit): McLowiczApi =
            retrofit.create(McLowiczApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): McLowiczDatabase =
            Room.databaseBuilder(app, McLowiczDatabase::class.java, "mclowicz_database")
                    .build()
}