package hu.bme.aut.carlog.data

import androidx.room.*

@Dao
interface ServiceDao {
    @Transaction
    @Query("SELECT * FROM CarList")
    fun getServiceList(): List<ServiceRelation>

    @Query("SELECT * FROM serviceList WHERE carId = :id")
    fun getServiceListByCarId(id: Long): List<Service>

    //vararg can pass one or more arguments, like an array of things
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg services: Service)
    @Update
    fun updateService(vararg service: Service)

    @Delete
    fun delete(vararg service: Service)


}