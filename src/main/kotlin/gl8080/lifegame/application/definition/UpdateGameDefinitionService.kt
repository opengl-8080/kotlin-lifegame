package gl8080.lifegame.application.definition

import gl8080.lifegame.logic.definition.GameDefinition
import gl8080.lifegame.logic.definition.GameDefinitionRepository
import gl8080.lifegame.web.resource.LifeGameDto
import org.slf4j.Logger
import javax.ejb.Stateless
import javax.inject.Inject

@Stateless
open class UpdateGameDefinitionService {
    @Inject
    lateinit private var logger: Logger
    @Inject
    lateinit private var gameDefinitionRepository: GameDefinitionRepository

    open fun update(dto: LifeGameDto): GameDefinition {
        this.logger.info("update game definition (id={})", dto.id)
        this.logger.debug("dto = {}", dto)

        val gameDefinition = this.gameDefinitionRepository.searchWithLock(dto.getId())

        dto.eachCell { position, status -> gameDefinition.setStatus(position, status) }
        gameDefinition.setVersion(dto.version)

        return gameDefinition
    }
}