package gl8080.lifegame.logic

import javax.persistence.Entity

/**
 * セルを表すクラス。
 */
@Entity
class Cell private constructor(alive: Boolean) : AbstractEntity(), LifeGameCell {

    private var alive: Boolean = alive
    @Transient
    private var nextStatus: Boolean? = null
    @Transient
    private var neighbors: List<Cell> = listOf()

    companion object {

        /**
         * 死んだセルを生成します。
         * @return 死んだセル
         */
        fun dead(): Cell {
            return Cell(false)
        }

        /**
         * 生きたセルを生成します。
         * @return 生きたセル
         */
        fun alive(): Cell {
            return Cell(true)
        }
    }

    override fun isAlive(): Boolean {
        return this.alive
    }

    /**
     * 次の状態を予約します。
     * <p>
     * このメソッドは、ライフゲームのルールに従ってこのセルの次の状態を予約します。
     * <p>
     * このメソッドを実行しただけでは、セルの状態は変更されません。<br>
     * 次の状態を確認するには {@link Cell#toBeAlive() toBeAlive()} メソッドを使用してください。<br>
     * 次の状態に遷移するには {@link Cell#stepNextStatus() stepNextStatus()} メソッドを使用してください。
     *
     * @see
     * <a href="https://ja.wikipedia.org/wiki/%E3%83%A9%E3%82%A4%E3%83%95%E3%82%B2%E3%83%BC%E3%83%A0#.E3.83.A9.E3.82.A4.E3.83.95.E3.82.B2.E3.83.BC.E3.83.A0.E3.81.AE.E3.83.AB.E3.83.BC.E3.83.AB">
     *   ライフゲームのルール | Wikipedia
     * </a>
     */
    fun reserveNextStatus() {
        val liveCellCount = this.neighbors.filter { it.isAlive() }.count()

        if (4 <= liveCellCount) {
            this.nextStatus = false
        } else if (liveCellCount == 3) {
            this.nextStatus = true
        } else if (liveCellCount == 2) {
            this.nextStatus = this.alive
        }  else {
            this.nextStatus = false
        }
    }

    /**
     * 次の状態に遷移します。
     * <p>
     * このメソッドの実行が完了すると、セルの状態は変更されます。<br>
     * そして、このセルは再び<u>予約されていない状態</u>に戻ります。
     *
     * @throws IllegalStateException 次の状態が予約されていない状態でこのメソッドを実行した場合
     */
    fun stepNextStatus() {
        if (this.nextStatus == null) {
            throw IllegalStateException("次の状態への遷移が予約されていません。")
        }

        this.alive = if (this.nextStatus as Boolean) true else false
        this.nextStatus = null
    }

    /**
     * 現在予約されている、次の状態を取得します。
     *
     * @return 次の状態（生存の場合は {@code true}）
     * @throws IllegalStateException 次の状態が予約されていない状態でこのメソッドを実行した場合
     */
    fun toBeAlive(): Boolean {
        if (this.nextStatus == null) {
            throw IllegalStateException("次の状態への遷移が予約されていません。")
        }

        return this.nextStatus as Boolean
    }

    /**
     * このセルに隣接する周辺のセルを設定する。
     *
     * @param neighbors 周辺のセル
     * @throws NullPointerException 周辺のセルが {@code null} の場合
     */
    fun setNeighbors(neighbors: List<Cell>) {
        this.neighbors = neighbors
    }

    /**
     * このセルに隣接する周辺のセルを取得する。
     * <p>
     * このメソッドが返すリストは、このオブジェクトが持つリストのコピーです。<br>
     * 取得したリストから要素を追加、削除しても、このオブジェクトが持つリストには影響を与えません。
     *
     * @return このセルに隣接する周辺のセル
     */
    fun getNeighbors(): List<Cell> {
        return this.neighbors
    }

    override fun toString(): String{
        return "Cell(id=${this.getId()}, alive=$alive, nextStatus=$nextStatus, neighbors=****)"
    }

    @Deprecated("このコンストラクタはフレームワークから使用されることを想定しています。")
    private constructor(): this(false)
}

