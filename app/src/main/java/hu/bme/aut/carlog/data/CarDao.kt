package hu.bme.aut.carlog.data

import androidx.room.*

@Dao
interface CarDao {
    @Query("SELECT * FROM carList")
    fun getAll(): List<Car>

    @Query("SELECT * FROM carList WHERE carList.Id = :id")
    fun getCarById(id : Long?): Car

    @Insert
    fun insert(car: Car): Long

    @Update
    fun update(car: Car)

    @Delete
    fun deleteCar(car: Car)
}