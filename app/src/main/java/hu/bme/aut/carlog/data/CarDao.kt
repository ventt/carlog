package hu.bme.aut.carlog.data

import androidx.room.*

@Dao
interface CarDao {
    @Query("SELECT * FROM carList")
    fun getAll(): List<Car>

    @Insert
    fun insert(car: Car): Long

    @Update
    fun update(car: Car)

    @Delete
    fun deleteCar(car: Car)
}