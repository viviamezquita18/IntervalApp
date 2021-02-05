package com.app.intervalapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.intervalapp.data.model.Interval
import com.app.intervalapp.data.model.IntervalWithTimer
import com.app.intervalapp.data.model.MyTimer

@Dao
abstract class IntervalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertInterval(interval: Interval): Long

    @Delete
    abstract suspend fun deleteInterval(interval: Interval)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTimers(timerList: List<MyTimer>)

    @Transaction
    @Query("SELECT * FROM interval_table")
    abstract fun getAllIntervals(): LiveData<List<IntervalWithTimer>>

    suspend fun insertIntervalWithTimer(interval: Interval, timerList: List<MyTimer>) {
        val id = insertInterval(interval)
        timerList.forEach {
            it.id_interval = id.toInt()
        }
        insertTimers(timerList)
    }
}