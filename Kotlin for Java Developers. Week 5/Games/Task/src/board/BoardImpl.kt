package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl<T>(width)


class GameBoardImpl<T>(width: Int) : SquareBoardImpl(width), GameBoard<T> {

    private val storage: MutableMap<Cell, T?> = hashMapOf()

    init {
        getAllCells().forEach { set(it, null)}
    }

    override fun get(cell: Cell): T? = storage[cell]

    override fun set(cell: Cell, value: T?) {
        storage[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> =
        storage.filter { predicate(it.value) }.keys


    override fun find(predicate: (T?) -> Boolean): Cell? =
        filter(predicate).firstOrNull()

    override fun any(predicate: (T?) -> Boolean): Boolean =
        !filter(predicate).isEmpty()

    override fun all(predicate: (T?) -> Boolean): Boolean =
        filter(predicate).size == storage.size
}

data class CellImpl(override val i: Int, override val j: Int) : Cell

open class SquareBoardImpl(final override val width: Int) : SquareBoard {

    private val cells = (1..width).flatMap { i -> (1..width).map { CellImpl(i, it) } }

    override fun getCellOrNull(i: Int, j: Int): Cell? =
            cells.filter { it.i == i }.filter { it.j == j }.firstOrNull()

    override fun getCell(i: Int, j: Int): Cell = getCellOrNull(i,j)!!

    override fun getAllCells(): Collection<Cell> = cells

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val row = cells.filter { it.i == i }
        return jRange.takeWhile { it <= width }.map { step -> row.find { cell -> cell.j == step  } as Cell }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val column = cells.filter { it.j == j }
        return iRange.takeWhile { it <= width }.map { step -> column.find { cell -> cell.i == step  } as Cell }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? =
        when(direction) {
            DOWN -> getCellOrNull(i+1, j)
            UP -> getCellOrNull(i-1, j)
            LEFT -> getCellOrNull(i, j-1)
            RIGHT -> getCellOrNull(i, j+1)
        }
}