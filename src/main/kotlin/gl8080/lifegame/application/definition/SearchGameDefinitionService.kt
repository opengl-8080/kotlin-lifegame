package gl8080.lifegame.application.definition

import gl8080.lifegame.logic.definition.GameDefinition
import gl8080.lifegame.logic.definition.GameDefinitionRepository
import org.slf4j.Logger
import javax.ejb.Stateless
import javax.inject.Inject


@Stateless
open class SearchGameDefinitionService {
    @Inject
    lateinit private var logger: Logger
    @Inject
    lateinit private var gameDefinitionRepository: GameDefinitionRepository

    open fun search(id: Long): GameDefinition {
        this.logger.debug("search game definition (id={})", id)
        return this.gameDefinitionRepository.search(id)
    }
}