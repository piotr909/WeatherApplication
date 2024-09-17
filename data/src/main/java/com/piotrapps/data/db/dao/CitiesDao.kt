package com.piotrapps.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piotrapps.data.db.entity.CityEntity

@Dao
interface CitiesDao {

    @Query("SELECT * FROM cities")
    fun getCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCity(city: CityEntity)

}