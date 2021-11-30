package hu.bme.aut.carlog.sqlite

import android.database.sqlite.SQLiteDatabase
import android.util.Log

object DbConstants {
    const val DATABASE_NAME = "carlog.db"
    const val DATABASE_VERSION = 1

    object Cars {
        const val DATABASE_TABLE = "cars"

        enum class Columns {
            ID, NAME, MANUFACTURER, TYPE
        }

        private val DATABASE_CREATE = """create table if not exists $DATABASE_TABLE (
            ${Columns.ID.name} integer primary key autoincrement,
            ${Columns.NAME} string not null,
            ${Columns.MANUFACTURER} string not null,
            ${Columns.TYPE} string not null,
            );"""
        //TODO: should implement a database of manufacturers and their types and join them by ID

        private const val DATABASE_DROP = "drop table if exists $DATABASE_TABLE;"

        fun onCreate(database: SQLiteDatabase) {
            database.execSQL(DATABASE_CREATE)
        }

        fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            Log.w(
                Cars::class.java.name,
                "Upgrading from version $oldVersion to $newVersion"
            )
            database.execSQL(DATABASE_DROP)
            onCreate(database)
        }
    }
}