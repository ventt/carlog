package hu.bme.aut.carlog.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import hu.bme.aut.carlog.model.Car

class PersistentDataHelper(context: Context) {
    private var database: SQLiteDatabase? = null
    private val dbHelper: DbHelper = DbHelper(context)

    private val carsColumns = arrayOf(
            DbConstants.Cars.Columns.ID.name,
        DbConstants.Cars.Columns.NAME.name,
        DbConstants.Cars.Columns.MANUFACTURER.name,
        DbConstants.Cars.Columns.TYPE.name
    )
    @Throws(SQLiteException::class)
    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

    fun persistCars(cars: List<Car>) {
        clearCars()
        for (car in cars) {
            val values = ContentValues()
            values.put(DbConstants.Cars.Columns.NAME.name, car.name);
            values.put(DbConstants.Cars.Columns.MANUFACTURER.name, car.manufacturer);
            values.put(DbConstants.Cars.Columns.TYPE.name, car.type);
            database!!.insert(DbConstants.Cars.DATABASE_TABLE, null, values)
        }
    }
    fun restoreCars(): MutableList<Car> {
        val cars: MutableList<Car> = ArrayList()
        val cursor: Cursor = database!!.query(DbConstants.Cars.DATABASE_TABLE, carsColumns, null,null,null,null,null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast){
            val car: Car = cursorToPoint(cursor)
            cars.add(car)
            cursor.moveToNext()
        }
        cursor.close()
        return cars
    }

    private fun cursorToPoint(cursor: Cursor): Car {
        val car = Car()
        car.name = cursor.getString(DbConstants.Cars.Columns.NAME.ordinal)
        car.manufacturer = cursor.getString(DbConstants.Cars.Columns.MANUFACTURER.ordinal)
        car.type = cursor.getString(DbConstants.Cars.Columns.TYPE.ordinal)
        return car
    }

    private fun clearCars() {
        database!!.delete(DbConstants.Cars.DATABASE_TABLE, null, null)
    }
}