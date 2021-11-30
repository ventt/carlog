package hu.bme.aut.carlog.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        DbConstants.Cars.onCreate(sqLiteDatabase)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        DbConstants.Cars.onUpgrade(sqLiteDatabase,oldVersion,newVersion)
    }
}