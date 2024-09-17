package com.piotrapps.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piotrapps.data.db.entity.CityEntity
import com.piotrapps.data.db.dao.CitiesDao

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class CitiesDatabase : RoomDatabase() {
    abstract val citiesDao: CitiesDao
}