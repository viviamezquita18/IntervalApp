package com.app.intervalapp.data.model

import androidx.room.Embedded
import androidx.room.Relation


data class IntervalWithTimer(
    @Embedded val interval: Interval,
    @Relation(
        parentColumn = "id_interval",
        entityColumn = "id_interval"
    )
    val timer: List<MyTimer>
)