package gl8080.lifegame.logic.definition

import gl8080.lifegame.logic.AbstractEntity
import gl8080.lifegame.logic.LifeGameCell
import javax.persistence.Entity
import javax.persistence.Table

/**
 * セル定義を表すクラス。
 */
@Entity
@Table(name="CELL_DEFINITION")
class CellDefinition : AbstractEntity(), LifeGameCell {

    private var alive: Boolean = false

    /**
     * このセル定義の生死の状態を設定する。
     * @param alive 生存の場合は {@code true} を指定する。
     */
    fun setStatus(alive: Boolean) {
        this.alive = alive
    }

    override fun isAlive(): Boolean {
        return this.alive
    }

    override fun toString(): String{
        return "CellDefinition(id=${this.getId()}, alive=$alive)"
    }
}
