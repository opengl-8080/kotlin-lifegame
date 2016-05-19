package gl8080.lifegame.persistence

import gl8080.lifegame.logic.definition.GameDefinition
import gl8080.lifegame.logic.definition.GameDefinitionRepository
import gl8080.lifegame.logic.exception.NotFoundEntityException
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.LockModeType
import javax.persistence.PersistenceContext

@ApplicationScoped
class JpaGameDefinitionRepository: GameDefinitionRepository {

    @PersistenceContext(unitName = "LifeGameUnit")
    lateinit private var em: EntityManager

    override fun register(gameDefinition: GameDefinition) {
        this.em.persist(gameDefinition)
    }

    override fun search(id: Long): GameDefinition {
        return this.searchWithLock(id, LockModeType.NONE)
    }

    override fun searchWithLock(id: Long): GameDefinition {
        return this.searchWithLock(id, LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    }

    private fun searchWithLock(id: Long, lock: LockModeType): GameDefinition {
        return this.em.find(GameDefinition::class.java, id, lock)
                ?: throw NotFoundEntityException(id)
    }

    override fun remove(gameDefinition: GameDefinition) {
        this.em.remove(gameDefinition)
    }
}
