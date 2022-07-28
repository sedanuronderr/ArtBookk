package com.seda.artbookk.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seda.artbookk.MealTypeConverter

@Database(entities = [Art::class],version=1)
@TypeConverters(MealTypeConverter::class)

abstract class ArtDatabase :RoomDatabase(){

    abstract fun artDao():ArtDao
}