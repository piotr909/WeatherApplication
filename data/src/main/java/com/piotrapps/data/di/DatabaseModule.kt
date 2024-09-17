package com.piotrapps.data.di

import android.content.Context
import androidx.room.Room
import com.piotrapps.data.db.CitiesDatabase
import com.piotrapps.data.db.dao.CitiesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CitiesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CitiesDatabase::class.java,
            "cities_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCitiesDao(citiesDatabase: CitiesDatabase): CitiesDao {
        return citiesDatabase.citiesDao
    }
}