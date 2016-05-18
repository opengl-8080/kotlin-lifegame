package gl8080.lifegame.persistence

import gl8080.lifegame.logic.Game
import gl8080.lifegame.logic.GameRepository
import gl8080.lifegame.logic.exception.NotFoundEntityException
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.LockModeType
import javax.persistence.PersistenceContext

@ApplicationScoped
class JpaGameRepository: GameRepository {

    @PersistenceContext(unitName = "LifeGameUnit")
    lateinit private var em: EntityManager

    override fun register(game: Game) {
        this.em.persist(game)
    }

    override fun search(id: Long): Game {
        return this.searchWithLock(id, LockModeType.NONE)
    }

    override fun searchWithLock(id: Long): Game {
        return this.searchWithLock(id, LockModeType.PESSIMISTIC_WRITE)
    }

    private fun searchWithLock(id: Long, type: LockModeType): Game {
        return this.em.find(Game::class.java, id, type)
                ?: throw NotFoundEntityException(id)
    }

    override fun remove(game: Game) {
        this.em.remove(game)
    }

}
