package games.gameOfFifteen

import java.util.*
import kotlin.streams.toList

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized first 15 cells on a board
     * (the last cell is empty)
     */
    val initialPermutation: List<Int>
}

fun Random.findAPermutation(): List<Int> {
    val set = mutableSetOf<Int>()
    while (set.size < 15) {
        val element = this.nextInt(16)
        if (element != 0) set.add(element)
    }
    return set.asSequence().toList()
}

class RandomGameInitializer : GameOfFifteenInitializer {
    override val initialPermutation by lazy {
        val random = Random()
        var result = random.findAPermutation()
        while (!isEven(result)) {
            result = random.findAPermutation()
        }
        result
    }
}

