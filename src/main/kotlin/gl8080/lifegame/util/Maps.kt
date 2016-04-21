package gl8080.lifegame.util

class Maps_<K, V> private constructor() {

    companion object MapsObject_ {

        fun <K, V> map(key: K, value: V) : Map<K, V> {
            return Maps_<K, V>().put(key, value).get()
        }

        fun <K, V> newMap() : Maps_<K, V> {
            return Maps_()
        }
    }

    private val map : MutableMap<K, V> = mutableMapOf()

    fun put(key: K, value: V) : Maps_<K, V> {
        this.map.put(key, value)
        return this
    }

    fun get() : Map<K, V> {
        return this.map
    }
}
