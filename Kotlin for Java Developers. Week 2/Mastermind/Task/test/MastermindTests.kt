package mastermind

import org.junit.Assert
import org.junit.Test

class MastermindTests {

    private fun testEvaluation(secret: String, guess: String, positions: Int, letters: Int) {
        val expected = Evaluation(positions, letters)
        val evaluation = evaluateGuess(secret, guess)
        Assert.assertEquals("Wrong evaluation for secret=$secret, guess=$guess",
                expected, evaluation)
    }

    // simple
    @Test
    fun testEqual() = testEvaluation("ABCD", "ABCD", 4, 0)

    @Test
    fun testOnlyWrongPositions() = testEvaluation("DCBA", "ABCD", 0, 4)

    @Test
    fun testSwap() = testEvaluation("ABCD", "ABDC", 2, 2)

    @Test
    fun testRightPositions() = testEvaluation("ABCD", "ABCF", 3, 0)

    @Test
    fun testWrongPositions() = testEvaluation("DAEF", "FECA", 0, 3)


    // repeated letters
    @Test
    fun testRightPosition() = testEvaluation("AABC", "ADFE", 1, 0)

    @Test
    fun testSameLetters() = testEvaluation("AABC", "DEAA", 0, 2)


    @Test
    fun test1() = testEvaluation("ADFE", "AABC", 1, 0)


    @Test
    fun test2() = testEvaluation("FBAE", "CBFE", 2, 1)


    @Test
    fun test3() = testEvaluation("CFDF", "FCCD", 0, 3)

    @Test
    fun test4() = testEvaluation("BDAD", "AAAE", 1, 0)

    @Test
    fun test5() = testEvaluation("DAAE", "AABC", 1, 1)
}