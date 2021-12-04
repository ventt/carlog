package hu.bme.aut.carlog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.sql.Date


@Entity(tableName = "serviceList")
data class Service (
    @ColumnInfo(name = "serviceId") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "carId") var carId: Long? = null,
    @ColumnInfo(name = "service_type") var service_type: String? = null,
    @ColumnInfo(name = "odometer") var mileage: Int? = null,
    @ColumnInfo(name = "date") var date: String? = null,
    @ColumnInfo(name = "desc") var description: String,
    @ColumnInfo(name = "oil_change") var oil_change: Boolean,
    @ColumnInfo(name = "oil_change_date") var oil_change_date: String
)
