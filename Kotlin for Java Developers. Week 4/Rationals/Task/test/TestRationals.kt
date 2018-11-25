package rationals

import org.junit.Assert
import org.junit.Test

class TestRationals {
    @Test
    fun test0() {
        val r1 = 1 divBy 2
        val r2 = 2000000000L divBy 4000000000L
        Assert.assertEquals("""Wrong result for
            |val r1 = 1 divBy 2
            |val r2 = 2000000000L divBy 4000000000L
            |r1 == r2""".trimMargin(),
                true, r1 == r2)
    }

    @Test
    fun test1() {
        Assert.assertEquals("Wrong result for\n(2 divBy 1).toString() == \"2\"",
                true, (2 divBy 1).toString() == "2")
    }

    @Test
    fun test2() {
        Assert.assertEquals("Wrong result for\n(-2 divBy 4).toString() == \"-1/2\"",
                true, (-2 divBy 4).toString() == "-1/2")
    }

    @Test
    fun test3() {
        Assert.assertEquals("Wrong result for\n\"117/1098\".toRational().toString() == \"13/122\"",
                true, "117/1098".toRational().toString() == "13/122")
    }

    @Test
    fun test4() {
        Assert.assertEquals("Wrong result for\n\"1/2\".toRational() - \"1/3\".toRational() == \"1/6\".toRational()",
                true, "1/2".toRational() - "1/3".toRational() == "1/6".toRational())
        Assert.assertEquals("Wrong result for\n\"1/2\".toRational() + \"1/3\".toRational() == \"5/6\".toRational()",
                true, "1/2".toRational() + "1/3".toRational() == "5/6".toRational())
    }

    @Test
    fun test5() {
        Assert.assertEquals("Wrong result for\n-(1 divBy 2) == (-1 divBy 2)",
                true, -(1 divBy 2) == (-1 divBy 2))
    }

    @Test
    fun test6() {
        Assert.assertEquals("Wrong result for\n(1 divBy 2) * (1 divBy 3) == \"1/6\".toRational()",
                true, (1 divBy 2) * (1 divBy 3) == "1/6".toRational())
        Assert.assertEquals("Wrong result for\n(1 divBy 2) / (1 divBy 4) == \"2\".toRational()",
                true, (1 divBy 2) / (1 divBy 4) == "2".toRational())
    }

    @Test
    fun test7() {
        Assert.assertEquals("Wrong result for\n(1 divBy 2) < (2 divBy 3)",
                true, (1 divBy 2) < (2 divBy 3))
        Assert.assertEquals("Wrong result for\n(1 divBy 2) in (1 divBy 3)..(2 divBy 3)",
                true, (1 divBy 2) in (1 divBy 3)..(2 divBy 3))
    }

    @Test
    fun test8() {
        Assert.assertEquals("Wrong result for\n" +
                "\"912016490186296920119201192141970416029\".toBigInteger() divBy " +
                "\"1824032980372593840238402384283940832058\".toBigInteger() == 1 divBy 2",
                true, "912016490186296920119201192141970416029".toBigInteger() divBy
                "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
    }

    @Test
    fun test9() {
        Assert.assertEquals("Wrong result for\n" +
                "\"5670711258187766016096\".toBigInteger() divBy " +
                "\"1017819969418316977248\".toBigInteger() == 39 divBy 7",
                true, "5670711258187766016096".toBigInteger() divBy
                "1017819969418316977248".toBigInteger() == 39 divBy 7)
    }

    @Test
    fun test10() {
        Assert.assertEquals("Wrong result for\n" +
                "\"-578136305229133309744\".toBigInteger() divBy " +
                "\"-966904753430936619984\".toBigInteger() == 461 divBy 771",
                true, "-578136305229133309744".toBigInteger() divBy
                "-966904753430936619984".toBigInteger() == 461 divBy 771)
    }

}