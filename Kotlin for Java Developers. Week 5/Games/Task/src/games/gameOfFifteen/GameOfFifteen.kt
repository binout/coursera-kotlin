package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'
 * (or choosing the corresponding run configuration).
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {

    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        val allCells = board.getAllCells()
        initializer.initialPermutation.forEachIndexed { index, i ->
            board[allCells.elementAt(index)] = i
        }
    }

    override fun canMove(): Boolean {
        return false
    }

    override fun hasWon(): Boolean {
        return false
    }

    override fun processMove(direction: Direction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

