package gl8080.lifegame.util

import java.util.function.Supplier

/**
 * 二重ループをラムダで実行できるようにするためのクラス。
 * <p>
 * このクラスが扱う二重ループは、縦と横が同じ大きさのループになります。
 */
object NestedLoop {

    /**
     * 二重ループを実行して、入れ子の {@code List} を生成する。
     * @param size サイズ
     * @param supplier 入れ子の {@code List} に入れる各要素を供給する処理
     * @return 入れ子の {@code List}
     */
    fun <T> collectList(size: Int, supplier: (Int, Int) -> T): List<List<T>> {
        val matrix = mutableListOf<List<T>>()

        for (i in 0..size-1) {
            val row = mutableListOf<T>()

            for (j in 0..size-1) {
                row.add(supplier(i, j))
            }

            matrix.add(row)
        }

        return matrix
    }

    /**
     * 二重ループを実行して、 {@code Map} を生成する。
     * @param size サイズ
     * @param keySupplier キーを生成する処理
     * @param valueSupplier バリューを生成する処理
     * @return 生成された {@code Map}
     */
    fun <K, V> collectMap(size: Int, keySupplier: (Int, Int) -> K, valueSupplier: () -> V): Map<K, V> {
        val map = mutableMapOf<K, V>()

        for (i in 0..size-1) {
            for (j in 0..size-1) {
                val key = keySupplier(i, j)
                val value = valueSupplier()

                map.put(key, value)
            }
        }

        return map
    }

    /**
     * 入れ子の {@code List} を反復処理する。
     * @param nestedList 入れ子の {@code List}
     * @param iterator 反復処理（１つ目と２つ目の引数にはループインデックスが渡され、３つ目の引数に {@code List} の要素が渡されます）
     */
    fun <T> each(nestedList: List<List<T>>, iterator: TriConsumer<Int, Int, T>) {
        for (i in nestedList.indices) {
            val row = nestedList[i]
            for (j in row.indices) {
                iterator.accept(i, j, row[j])
            }
        }
    }

    /**
     * 入れ子の {@code List} を反復処理する。
     * @param nestedList 入れ子の {@code List}
     * @param iterator 反復処理（１つ目と２つ目の引数にはループインデックスが渡され、３つ目の引数に {@code List} の要素が渡されます）
     */
    fun <T> each(nestedList: List<List<T>>, iterator: (Int, Int, T) -> Unit) {
        for (i in nestedList.indices) {
            val row = nestedList[i]
            for (j in row.indices) {
                iterator(i, j, row[j])
            }
        }
    }
}
