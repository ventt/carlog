package hu.bme.aut.carlog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "serviceList")
data class Service (
    @ColumnInfo(name = "serviceId") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "carId") var carId: Long? = null,
    @ColumnInfo(name = "desc") var description: String,
)