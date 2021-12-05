package hu.bme.aut.carlog.data

import androidx.room.*

@Dao
interface fillUpDao {
    @Query("SELECT * FROM fillUp")
    fun getAll(): List<fillUp>

    @Query("SELECT * FROM fillUp WHERE fillUp.carId = :id")
    fun getFuellingFromCarId(id : Long?): List<fillUp>

    @Insert
    fun insert(fillUp: fillUp)

    @Update
    fun update(fillUp: fillUp)

    @Delete
    fun deleteFillUp(fillUp: fillUp)
}