package gl8080.lifegame.util

@FunctionalInterface
interface TriConsumer<S, T, U> {

    fun accept(s: S, t: T, u: U)
}
