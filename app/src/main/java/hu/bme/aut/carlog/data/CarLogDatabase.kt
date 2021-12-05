package hu.bme.aut.carlog.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = arrayOf(Car::class, Service::class, fillUp::class),
    version = 4,
)
abstract class CarLogDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
    abstract fun ServiceDao(): ServiceDao
    abstract fun fillUpDao(): fillUpDao

    companion object {
        private val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE `carList`")
            }
        }
        private val MIGRATION_2_3 = object : Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE `carList`")
            }
        }
        private val MIGRATION_3_4 = object : Migration(3,4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE `carList`")
            }
        }

        fun getDatabase(applicationContext: Context): CarLogDatabase {
            return Room.databaseBuilder(
                applicationContext,
                CarLogDatabase::class.java,
                "cars"
            ).fallbackToDestructiveMigration().build()
        }
    }
}