package com.app.intervalapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "timer_table")
data class MyTimer(

    var millseconds: Long,
    var note: String,
    @PrimaryKey(autoGenerate = true)
    val id_timer: Int? = null,

    @ForeignKey
        (
        entity = Interval::class,
        parentColumns = ["id_interval"],
        childColumns = ["id_interval"],
        onUpdate = CASCADE,
        onDelete = CASCADE
    )
    var id_interval: Int? = null
)