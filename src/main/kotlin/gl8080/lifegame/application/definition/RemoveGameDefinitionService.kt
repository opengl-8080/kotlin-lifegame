package gl8080.lifegame.application.definition

import gl8080.lifegame.logic.definition.GameDefinitionRepository
import org.slf4j.Logger
import javax.ejb.Stateless
import javax.inject.Inject

@Stateless
open class RemoveGameDefinitionService {
    @Inject
    lateinit private var logger: Logger
    @Inject
    lateinit private var gameDefinitionRepository: GameDefinitionRepository

    open fun remove(id: Long) {
        this.logger.info("remove game definition (id={})", id)

        val gameDefinition = this.gameDefinitionRepository.searchWithLock(id)

        this.gameDefinitionRepository.remove(gameDefinition)
    }
}