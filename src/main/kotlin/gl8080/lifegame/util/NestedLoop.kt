package gl8080.lifegame.util

import java.util.function.Function
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
    fun <T> collectList(size: Int, supplier: BiIntSupplier<T>): List<List<T>> {
        val matrix = mutableListOf<List<T>>()

        for (i in 0..size) {
            val row = mutableListOf<T>()

            for (j in 0..size) {
                row.add(supplier.get(i, j))
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
    fun <K, V> collectMap(size: Int, keySupplier: BiIntSupplier<K>, valueSupplier: BiIntSupplier<V>): Map<K, V> {
        val map = mutableMapOf<K, V>()

        for (i in 0..size) {
            for (j in 0..size) {
                val key = keySupplier.get(i, j)
                val value = valueSupplier.get(i, j)

                map.put(key, value)
            }
        }

        return map
    }

    fun <K, V> collectMap(size: Int, keySupplier: BiIntSupplier<K>, valueSupplier: Supplier<V>): Map<K, V> {
        return collectMap(size, keySupplier, fun (i: Int, j: Int): V = valueSupplier.get())
    }


    fun foo(f: Function<String, String>) {

    }

    fun a() {
        foo(object: Function<String, String> {
            override fun apply(string: String): String {
                return ""
            }
        })
    }
}

