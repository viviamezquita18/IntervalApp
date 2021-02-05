package com.app.intervalapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interval_table")
data class Interval(
    var title: String,
    var sets: Int,
    @PrimaryKey(autoGenerate = true)
    val id_interval: Int? = null
)