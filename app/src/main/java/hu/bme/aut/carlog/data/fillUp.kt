package hu.bme.aut.carlog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.sql.Date

@Entity(tableName = "fillUp")
data class fillUp(
    @ColumnInfo(name = "fillUpId") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "carId") var carId: Long? = null,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "odometer") var odometer: Int,
    @ColumnInfo(name = "quantity") var quantity: Float,
    @ColumnInfo(name = "price") var price: Float,
    @ColumnInfo(name = "full_fueling") var full_fueling: Boolean
)


