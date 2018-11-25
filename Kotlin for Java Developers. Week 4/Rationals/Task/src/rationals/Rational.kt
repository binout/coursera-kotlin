package rationals

import java.math.BigInteger

class Rational(private val numerator: BigInteger,
               private val denominator: BigInteger = BigInteger.ONE)
    : Comparable<Rational> {

    init {
        require(denominator != BigInteger.ZERO)
    }

    operator fun unaryMinus(): Rational  = Rational(-numerator, denominator)

    operator fun minus(other: Rational): Rational = Rational(
            numerator * other.denominator - other.numerator * denominator,
            denominator * other.denominator)

    operator fun plus(other: Rational): Rational = Rational(
            numerator * other.denominator + other.numerator * denominator,
            denominator * other.denominator)

    operator fun times(other: Rational): Rational = Rational(
            numerator * other.numerator,
            denominator * other.denominator)

    operator fun div(other: Rational): Rational = times(other.inv())

    override fun compareTo(other: Rational): Int =
        (numerator * other.denominator).compareTo(other.numerator * denominator)

    fun inv(): Rational = Rational(denominator, numerator)

    fun opposite(): Rational = Rational(-numerator, - denominator)

    fun normalize(): Rational {
        val gcd = this.numerator.gcd(this.denominator)
        if (this.denominator < BigInteger.ZERO) {
            return Rational(this.numerator / gcd, this.denominator / gcd).opposite()
        } else {
            return Rational(this.numerator / gcd, this.denominator / gcd)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Rational) {
            return false
        } else {
            val normalize = this.normalize()
            val otherNormalize = other.normalize()
            return normalize.numerator == otherNormalize.numerator
                    && normalize.denominator == otherNormalize.denominator
        }
    }

    override fun toString(): String {
        val normalize = this.normalize()
        if (normalize.denominator == BigInteger.ONE) {
            return normalize.numerator.toString()
        }
        return "${normalize.numerator}/${normalize.denominator}"
    }

}

infix fun BigInteger.divBy(other: BigInteger): Rational = Rational(this, other)
infix fun Int.divBy(other: Int): Rational = Rational(this.toBigInteger(), other.toBigInteger())
infix fun Long.divBy(other: Long): Rational = Rational(this.toBigInteger(), other.toBigInteger())

fun String.toRational(): Rational {
    if (this.contains("/")) {
        val splitted = this.split("/", limit = 2)
        return splitted[0].toBigInteger() divBy splitted[1].toBigInteger()
    } else {
        return Rational(this.toBigInteger())
    }
}

fun main(args: Array<String>) {
    val r1 = 1 divBy 2
    val r2 = 2000000000L divBy 4000000000L
    println(r1 == r2)

    println((2 divBy 1).toString() == "2")

    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    println("1/2".toRational() - "1/3".toRational() == "1/6".toRational())
    println("1/2".toRational() + "1/3".toRational() == "5/6".toRational())

    println(-(1 divBy 2) == (-1 divBy 2))

    println((1 divBy 2) * (1 divBy 3) == "1/6".toRational())
    println((1 divBy 2) / (1 divBy 4) == "2".toRational())

    println((1 divBy 2) < (2 divBy 3))
    println((1 divBy 2) in (1 divBy 3)..(2 divBy 3))

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}

