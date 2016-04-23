package gl8080.lifegame.util

class Maps<K, V> private constructor() {

    companion object {

        fun <K, V> map(key: K, value: V) : Map<K, V> {
            return Maps<K, V>().put(key, value).get()
        }

        fun <K, V> newMap() : Maps<K, V> {
            return Maps()
        }
    }

    private val mutableMap: MutableMap<K, V> = mutableMapOf()

    fun put(key: K, value: V) : Maps<K, V> {
        this.mutableMap.put(key, value)
        return this
    }

    fun get() : Map<K, V> {
        return this.mutableMap
    }
}
