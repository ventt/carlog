package hu.bme.aut.carlog.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1)
abstract class CarLogDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {
        fun getDatabase(applicationContext: Context): CarLogDatabase{
            return Room.databaseBuilder(
                applicationContext,
                CarLogDatabase::class.java,
                "cars"
            ).build()
        }
    }
}