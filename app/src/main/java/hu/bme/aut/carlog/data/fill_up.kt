package hu.bme.aut.carlog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.sql.Date

@Entity(tableName = "fillUp")
data class fill_up(
    @ColumnInfo(name = "fillUpId") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "carId") var carId: Long? = null,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "odometer") var odometer: Int,
    @ColumnInfo(name = "quantity") var quantity: Float,
    @ColumnInfo(name = "price") var price: Float,
    @ColumnInfo(name = "fuel_type") var fuel_type: FuelType,
    @ColumnInfo(name = "full_fueling") var full_fueling: Boolean,
    @ColumnInfo(name = "desc") var description: String
){
    enum class FuelType{
        PREMIUM_GASOLINE_100_PLUS, BIO_ALCOHOL,
        E10_95, NORMAL_GASOLINE_91, PREMIUM_GASOLINE_100,
        PREMIUM_GASOLINE_95, SUPER_PLUS_GASOLINE_98, SUPER_GASOLINE_95, TWO_STROKE,
        BIO_DIESEL, DIESEL, GTL_DIESEL, PREMIUM_DIESEL, VEGETABLE_OIL;
        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): FuelType? {
                var ret: FuelType? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(category: FuelType): Int {
                return category.ordinal
            }
        }
    }
}

