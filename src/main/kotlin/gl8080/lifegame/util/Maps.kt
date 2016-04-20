package gl8080.lifegame.util

import java.util.*

class Maps_<K, V> {

    private val map : MutableMap<K, V> = mutableMapOf()

    fun put(key: K, value: V) : Maps_<K, V> {
        this.map.put(key, value)
        return this
    }
}
