package hu.bme.aut.carlog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "carList")
data class Car(
    @ColumnInfo(name = "Id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "manufacturer") var manufacturer: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "produceDate") var produceDate: String
)
