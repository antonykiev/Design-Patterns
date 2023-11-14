package beahavioural


fun main() {
    val customer = Customer(CarBookingStrategy())
    var fare = customer.calculateFare(5)
    println(fare)

    customer.bookingStrategy = TrainBookingStrategy()
    fare = customer.calculateFare(5)
    println(fare)
}

interface BookingStrategy {
    val fare: Double
}

class CarBookingStrategy : BookingStrategy {

    override val fare: Double = 12.5

    override fun toString(): String {
        return "CarBookingStrategy"
    }
}

class TrainBookingStrategy : BookingStrategy {

    override val fare: Double = 8.5

    override fun toString(): String {
        return "TrainBookingStrategy"
    }
}

class Customer(var bookingStrategy: BookingStrategy) {

    fun calculateFare(numOfPassengers: Int): Double {
        val fare = numOfPassengers * bookingStrategy.fare
        println("Calculating fares using $bookingStrategy")
        return fare
    }
}