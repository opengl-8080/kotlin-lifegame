package gl8080.lifegame.util


/**
 * 2つのint値オペランドを受け取って結果を返さないオペレーションを表します。
 */
@FunctionalInterface
interface BiIntSupplier<T> {

    abstract fun get(i: Int, j: Int): T
}