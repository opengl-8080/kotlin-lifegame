package gl8080.lifegame.web.resource

import gl8080.lifegame.logic.LifeGame
import gl8080.lifegame.logic.Position
import gl8080.lifegame.util.NestedLoop
import javax.ws.rs.BadRequestException


data class LifeGameDto(
    val id: Long?,
    val size: Int,
    val cells: List<List<Boolean>>,
    val version: Long?
) {

    companion object {
        /**
         * {@link LifeGame} からインスタンスを生成する。
         *
         * @param lifeGame {@link LifeGame}
         * @return 生成された DTO
         */
        fun of(lifeGame: LifeGame): LifeGameDto {
            val cells = lifeGame.getCells()

            val cellsStatus = NestedLoop.collectList(lifeGame.getSize(), {i, j ->
                cells[Position(i, j)]?.isAlive() ?: false
            })

            val dto = LifeGameDto(
                lifeGame.getId(),
                lifeGame.getSize(),
                cellsStatus,
                lifeGame.getVersion()
            )

            return dto
        }
    }

    /**
     * この DTO が持つ全てのセルに対して、指定した処理を適用します。
     * @param iterator 反復処理
     */
    fun eachCell(iterator: (Position, Boolean) -> Unit) {
        NestedLoop.each(this.cells, {i, j, status ->
            iterator(Position(i, j), status)
        })
    }

    fun getId(): Long {
        return this.id ?: throw BadRequestException("id が設定されていません。")
    }

    private constructor(): this(null, -1, listOf(), -1)
}