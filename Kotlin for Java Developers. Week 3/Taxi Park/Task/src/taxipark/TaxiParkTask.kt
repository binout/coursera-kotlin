package taxipark

import java.util.*
import kotlin.math.absoluteValue

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {
    val driversWhoPerformedAtrip = this.trips.map { it.driver }
    return this.allDrivers.filter { it !in driversWhoPerformedAtrip }.toSet()
}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        this.allPassengers.filter { p -> this.trips.count { trip -> p in trip.passengers } >= minTrips }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        this.allPassengers.filter { p -> this.trips.filter {  trip -> p in trip.passengers }.count{ trip -> trip.driver == driver } > 1 }.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    fun hasMajorityOfDiscount(p: Passenger): Boolean {
        val tripOfPassenger = this.trips.filter { trip -> p in trip.passengers }
        val noDiscountTrip = tripOfPassenger.count { it.discount == null}
        return !tripOfPassenger.isEmpty() && (noDiscountTrip / tripOfPassenger.size.toDouble()) < 0.5
    }
    return this.allPassengers.filter { hasMajorityOfDiscount(it)}.toSet()
}
/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    fun dozen(duration: Int) : IntRange {
        val i = ((duration / 10).absoluteValue)
        return 10*i..(10*i+9)
    }
    val tripByDozen: Map<IntRange, List<Trip>> = this.trips.groupBy { dozen(it.duration) }
    return tripByDozen.maxBy { it.value.size }?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (this.trips.isEmpty()) return false

    val totalCost = this.trips.map { it.cost }.sum()
    val oneOfFifth = (this.allDrivers.size / 5)
    val costByDriver = this.trips.groupBy { it.driver }.mapValues { it.value.map { it.cost }.sum() }
    var sum = 0.0
    val currentMap = costByDriver.toMutableMap()
    repeat(oneOfFifth) { _ ->
        val max = currentMap.maxBy { it.value }
        sum += max?.value ?: 0.0
        currentMap.remove(max!!.key)
    }
    return sum >= totalCost * 0.8
}
