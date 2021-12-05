package hu.bme.aut.carlog.data

object FuelConsumptionCalc {
    fun getConsumption(firstOdometer: Int, secondOdometer: Int, quantity: Float) : Float{
        return (quantity/((secondOdometer.toFloat() - firstOdometer.toFloat())/100) )
    }
    fun getTrip(firstOdometer: Int, secondOdometer: Int) : Float{
        return (secondOdometer.toFloat() - firstOdometer.toFloat())
    }
}