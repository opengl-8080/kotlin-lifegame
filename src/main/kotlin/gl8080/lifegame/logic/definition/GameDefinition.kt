package gl8080.lifegame.logic.definition

import gl8080.lifegame.logic.AbstractEntity
import gl8080.lifegame.logic.LifeGame
import gl8080.lifegame.logic.LifeGameCell
import gl8080.lifegame.logic.Position
import gl8080.lifegame.logic.exception.IllegalParameterException
import gl8080.lifegame.util.NestedLoop
import javax.persistence.*

/**
 * ゲーム定義を表すクラス。
 */
@Entity
@Table(name="GAME_DEFINITION")
class GameDefinition(
    private val size: Int
): AbstractEntity(), LifeGame {

    companion object {
        /**ゲームのサイズに指定できる最大値*/
        val MAX_SIZE = 50
    }

    @Version
    private var version: Long? = null

    @OneToMany(cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE))
    @JoinColumn(name="GAME_DEFINITION_ID")
    private val cells: Map<Position, CellDefinition>

    init {
        if (this.size < 1) {
            throw IllegalParameterException("サイズに０以外の値は指定できません size = ${this.size}")
        } else if (MAX_SIZE < this.size) {
            throw IllegalParameterException("サイズに $MAX_SIZE 以上の値は指定できません size = ${this.size}")
        }

        this.cells = NestedLoop.collectMap(this.size, {i: Int, j: Int -> Position(i, j)}, { -> CellDefinition()})
    }

    /**
     * 指定した位置のセル定義の状態を変更します。
     *
     * @param position 位置
     * @param status 生存に変更する場合は {@code true}
     * @throws IllegalArgumentException 位置が {@code null} の場合
     * @throws IllegalParameterException 位置に指定した場所にセル定義が存在しない場合
     */
    fun setStatus(position: Position, status: Boolean) {
        val cell = this.cells[position]
                 ?: throw IllegalParameterException("位置が範囲外です (size = ${this.size}, position = $position)")

        cell.setStatus(status)
    }

    override fun getCells(): Map<Position, LifeGameCell> = this.cells

    override fun getVersion() = this.version

    fun setVersion(version: Long) {
        this.version = version
    }

    override fun getSize() = this.size
    override fun toString(): String{
        return "GameDefinition(id=${this.getId()}, size=$size, version=$version, cells=$cells)"
    }

    @Deprecated(message="JPA 用", level = DeprecationLevel.ERROR)
    constructor() : this(1)
}
