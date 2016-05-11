package gl8080.lifegame.logic


/**
 * １つのライフゲームを表すインターフェース。
 */
interface LifeGame {

    /**
     * このゲームの ID を取得します。
     * @return ID
     */
    fun getId(): Long?

    /**
     * このゲームのサイズを取得します。
     * @return サイズ
     */
    fun getSize(): Int

    /**
     * このゲームが持つ全てのセルを取得します。
     * <p>
     * このメソッドが返すマップは、このオブジェクトが持つマップのコピーです。<br>
     * ここで取得したマップのエントリを削除するなどしても、このオブジェクトが持つオリジナルのマップには影響を与えません。
     *
     * @return 全てのセル
     */
    fun getCells(): Map<Position, LifeGameCell>

    /**
     * このゲームの状態を簡単な文字列形式で取得します。
     * <p>
     * {@code "+"} は生きたセルを、 {@code "-"} は死んだセルを表しています。
     *
     * @return ゲームの状態を表す文字列
     */
    fun dump(): String {
        val sb = StringBuilder()
        val size = this.getSize()
        for (i in 0..size) {
            for (j in 0..size) {
                val p = Position(i, j)
                val cell = this.getCells()[p]

                if (cell != null) {
                    sb.append(if (cell.isAlive()) "+" else "-")
                } else {
                    sb.append("?")
                }
                sb.append("\n")
            }
        }

        return sb.toString()
    }

    /**
     * このゲームのバージョン番号を取得する。
     * <p>
     * バージョン番号は、同時更新のチェックで利用します。
     * <p>
     * このメソッドのデフォルトは {@code null} を返します。<br>
     * サブクラスが同時更新のチェックを必要とする場合は、このメソッドをオーバーライドして、
     * そのインスタンスのバージョン番号を返すように実装してください。
     *
     * @return バージョン番号
     */
    open fun getVersion(): Long? {
        return null
    }
}