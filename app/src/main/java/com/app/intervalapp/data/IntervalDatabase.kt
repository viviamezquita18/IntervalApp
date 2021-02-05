package com.app.intervalapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.intervalapp.data.model.Interval
import com.app.intervalapp.data.model.MyTimer

@Database(
    entities = [Interval::class, MyTimer::class],
    version = 1
)
abstract class IntervalDatabase : RoomDatabase() {
    abstract val intervalDao: IntervalDao
}