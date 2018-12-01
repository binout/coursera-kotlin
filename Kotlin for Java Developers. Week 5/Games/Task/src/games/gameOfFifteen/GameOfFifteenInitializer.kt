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

class RandomGameInitializer : GameOfFifteenInitializer {
    override val initialPermutation by lazy {
        val random = Random()
        val set = mutableSetOf<Int>()
        while (set.size < 15) {
            val element = random.nextInt(16)
            if (element != 0) set.add(element)
        }
        set.asSequence().toList()
    }
}

