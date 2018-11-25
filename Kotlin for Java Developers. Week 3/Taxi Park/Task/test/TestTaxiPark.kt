package taxipark

import org.junit.Assert
import org.junit.Test

class TestTaxiPark {
    @Test
    fun testFakeDrivers() {
        val tp = taxiPark(1..3, 1..2, trip(1, 1), trip(1, 2))
        Assert.assertEquals("Wrong result for 'findFakeDrivers()'." + tp.display(),
                drivers(2, 3).toSet(), tp.findFakeDrivers())
    }

    @Test
    fun testFaithfulPassengers() {
        val tp = taxiPark(1..3, 1..5, trip(1, 1), trip(2, 1), trip(1, 4), trip(3, 4), trip(1, 5), trip(2, 5), trip(2, 2))
        Assert.assertEquals("Wrong result for 'findFaithfulPassengers()'. MinTrips: 2." + tp.display(),
                passengers(1, 4, 5), tp.findFaithfulPassengers(2))
    }

    @Test
    fun testFrequentPassengers() {
        val tp = taxiPark(1..2, 1..4, trip(1, 1), trip(1, 1), trip(1, listOf(1, 3)), trip(1, 3), trip(1, 2), trip(2, 2))
        Assert.assertEquals("Wrong result for 'findFrequentPassengers()'. Driver: ${driver(1).name}." + tp.display(),
                passengers(1, 3), tp.findFrequentPassengers(driver(1)))
    }

    @Test
    fun testSmartPassengers() {
        val tp = taxiPark(1..2, 1..2, trip(1, 1, discount = 0.1), trip(2, 2))
        Assert.assertEquals("Wrong result for 'findSmartPassengers()'." + tp.display(),
                passengers(1), tp.findSmartPassengers())
    }

    @Test
    fun testSmartPassengers2() {
        val tp = taxiPark(1..2, 1..10)
        Assert.assertEquals("Wrong result for 'findSmartPassengers()'." + tp.display(),
                emptySet<Passenger>(), tp.findSmartPassengers())
    }

   @Test
    fun testSmartPassengers3() {
        val tp = taxiPark(0..5, 0..9,
                trip(5, 2, 33, 18.0),
                trip(3, listOf(5, 9), 29, 16.0, 0.3),
                trip(2, listOf(5, 3, 8), 0, 3.0),
                trip(1, listOf(4, 8), 32, 6.0),
                trip(0, listOf(1), 37, 28.0),
                trip(2, listOf(10, 8), 0, 7.0),
                trip(2, listOf(9, 4), 25, 35.0),
                trip(0, listOf(3, 7), 30, 35.0),
                trip(2, listOf(2, 3), 17, 27.0, 0.4),
                trip(1, listOf(9, 5, 4), 5, 7.0))
        Assert.assertEquals("Wrong result for 'findSmartPassengers()'." + tp.display(),
                emptySet<Passenger>(), tp.findSmartPassengers())
    }

    @Test
    fun testTheMostFrequentTripDuration() {
        val tp = taxiPark(1..3, 1..5, trip(1, 1, duration = 10), trip(3, 4, duration = 30),
                trip(1, 2, duration = 20), trip(2, 3, duration = 35))
        // The period 30..39 is the most frequent since there are two trips (duration 30 and 35)
        Assert.assertEquals("Wrong result for 'findTheMostFrequentTripDurationPeriod()'.",
                30..39, tp.findTheMostFrequentTripDurationPeriod())
    }

    @Test
    fun testParetoPrincipleSucceeds() {
        val tp = taxiPark(1..5, 1..4,
                trip(1, 1, 20, 20.0),
                trip(1, 2, 20, 20.0),
                trip(1, 3, 20, 20.0),
                trip(1, 4, 20, 20.0),
                trip(2, 1, 20, 20.0))
        // The income of driver #1: 160.0;
        // the total income of drivers #2..5: 40.0.
        // The first driver constitutes exactly 20% of five drivers
        // and his relative income is 160.0 / 200.0 = 80%.

        Assert.assertEquals(
                "Wrong result for 'checkParetoPrinciple()'." + tp.display(),
                true, tp.checkParetoPrinciple())
    }

    @Test
    fun testParetoPrincipleFails() {
        val tp = taxiPark(1..5, 1..4,
                trip(1, 1, 20, 20.0),
                trip(1, 2, 20, 20.0),
                trip(1, 3, 20, 20.0),
                trip(2, 4, 20, 20.0),
                trip(3, 1, 20, 20.0))
        // The income of driver #1: 120.0;
        // the total income of drivers #2..5: 80.0.
        // The first driver constitutes 20% of five drivers
        // but his relative income is 120.0 / 200.0 = 60%
        // which is less than 80%.

        Assert.assertEquals(
                "Wrong result for 'checkParetoPrinciple()'." + tp.display(),
                false, tp.checkParetoPrinciple())
    }

    @Test
    fun testParetoPrincipleNoTrips() {
        val tp = taxiPark(1..5, 1..4)
        Assert.assertEquals(
                "Wrong result for 'checkParetoPrinciple()'." + tp.display(),
                false, tp.checkParetoPrinciple())
    }
}