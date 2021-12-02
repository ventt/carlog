package hu.bme.aut.carlog.data

import androidx.room.Embedded
import androidx.room.Relation


data class ServiceRelation(
    @Embedded val carList: Car,
    @Relation(
        parentColumn = "Id",
        entityColumn = "carId"
    )
    val serviceList: List<Service>
)
