package gl8080.lifegame.logic

import gl8080.lifegame.logic.exception.IllegalParameterException
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * 位置を表すクラス。
 * <p>
 * このクラスは、縦横の座標によってオブジェクトを一意に識別します。<br>
 * つまり、座標が同じ場所を指していれば、異なるインスタンスであっても {@link Position#equals(Object) equals()} メソッドは
 * {@code true} を返します。
 * <p>
 * 座標値は、 {@code 0} オリジンです。
 */
@Embeddable
data class Position(
    @Column(name="VERTICAL_POSITION")
    private val vertical: Int,
    @Column(name="HORIZONTAL_POSITION")
    private val horizontal: Int
) {

    init {
        if (this.vertical < 0 || this.horizontal < 0) {
            throw IllegalParameterException("座標にマイナスは指定できません (${this.vertical}, ${this.horizontal})")
        }
    }

    /**
     * この位置に隣接する、周囲８つの位置をリストで取得します。
     * <p>
     * 隣接する座標がマイナスになる場合、その座標を指す位置はリストから除外されます。
     *
     * @return この位置に隣接する周囲８つの位置オブジェクト
     */
    fun getNeighborPositions(): List<Position> {
        val neighbors = mutableListOf<Position>()

        for (v in (this.vertical - 1)..(this.vertical + 1)) {
            for (h in (this.horizontal - 1)..(this.horizontal + 1)) {
                if ((0 <= v && 0 <= h) && !(this.vertical==v && this.horizontal==h)) {
                    neighbors.add(Position(v, h))
                }
            }
        }

        return neighbors
    }

    @Deprecated(message = "JPA用", level = DeprecationLevel.ERROR)
    private constructor(): this(Int.MAX_VALUE, Int.MAX_VALUE)
}
