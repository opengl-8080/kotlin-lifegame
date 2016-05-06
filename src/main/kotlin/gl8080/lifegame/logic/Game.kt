package gl8080.lifegame.logic

import gl8080.lifegame.logic.definition.GameDefinition
import java.util.*
import javax.persistence.CascadeType.*
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany


/**
 * ゲームを表すクラス。
 *
 * @constructor 新しいゲームを作成する。
 * @param gameDef このゲームの元となるゲーム定義
 */
@Entity
class Game constructor(
    gameDef: GameDefinition
) : AbstractEntity(), LifeGame {

    private val size = gameDef.getSize()

    @OneToMany(cascade = arrayOf(PERSIST, MERGE, REMOVE))
    @JoinColumn(name="GAME_ID")
    private val cells: MutableMap<Position, Cell> = mutableMapOf()

    init {
        this.initializeCells(gameDef)
    }

    private fun initializeCells(gameDef: GameDefinition) {
        gameDef.getCells().forEach { entry ->
            val position = entry.key
            val cellDef = entry.value
            this.cells.put(position, if (cellDef.isAlive()) Cell.alive() else Cell.dead())
        }
    }

    /**
     * このゲームが持つ各セルに、隣接する周囲のセルをセットする。
     */
    fun initializeNeighborCells() {
        this.cells.forEach { entry ->
            val position = entry.key

            val neighbors = position
                            .getNeighborPositions()
                            .map { this.cells[it] }
                            .filterNotNull()

            val cell = entry.value
            cell.setNeighbors(neighbors)
        }
    }

    override fun getCells(): Map<Position, Cell> {
        return HashMap(this.cells)
    }

    /**
     * このゲームを１ステップ進める。
     */
    fun nextStep() {
        if (this.cells.any { it.value.getNeighbors().isEmpty() }) {
            throw IllegalStateException("隣接セルが初期化されていません。")
        }

        this.cells.values.forEach { it.reserveNextStatus() }
        this.cells.values.forEach { it.stepNextStatus() }
    }

    override fun getSize(): Int {
        return this.size
    }

    override fun toString(): String{
        return "Game(id=${this.getId()}, size=$size, cells=$cells)"
    }

    @Deprecated(message="JPA用", level= DeprecationLevel.ERROR)
    private constructor(): this(GameDefinition(1))
}