package com.app.intervalapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.app.intervalapp.data.model.Interval
import com.app.intervalapp.data.model.IntervalWithTimer
import com.app.intervalapp.data.model.MyTimer
import com.app.intervalapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class IntervalDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: IntervalDatabase
    private lateinit var dao: IntervalDao

    @Before
    fun createDB() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            IntervalDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.intervalDao
    }

    @After
    fun closeDB() {
        database.close()
    }

    @Test
    fun insertIntervalWithTimer() = runBlockingTest {
        val interval = Interval("title", 2, 1)
        val timers = listOf(MyTimer(300L, "note", 1))
        dao.insertIntervalWithTimer(interval, timers)
        val allIntervals = dao.getAllIntervals().getOrAwaitValue()
        assertThat(allIntervals).contains(IntervalWithTimer(interval, timers))
    }

    @Test
    fun deleteIntervalWithTimer() = runBlockingTest {
        val interval = Interval("title", 2, 1)
        val timers = listOf(MyTimer(300L, "note", 1))
        dao.insertIntervalWithTimer(interval, timers)
        dao.deleteInterval(interval)
        val allIntervals = dao.getAllIntervals().getOrAwaitValue()
        assertThat(allIntervals).doesNotContain(IntervalWithTimer(interval, timers))
    }

}