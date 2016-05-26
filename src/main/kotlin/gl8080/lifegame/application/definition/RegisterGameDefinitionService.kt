package gl8080.lifegame.application.definition

import gl8080.lifegame.logic.definition.GameDefinition
import gl8080.lifegame.logic.definition.GameDefinitionRepository
import org.slf4j.Logger
import javax.ejb.Stateless
import javax.inject.Inject


@Stateless
open class RegisterGameDefinitionService {
    @Inject
    lateinit private var logger: Logger
    @Inject
    lateinit private var gameDefinitionRepository: GameDefinitionRepository

    open fun register(size: Int): GameDefinition {
        this.logger.info("register game definition (size={})", size)

        val gameDefinition = GameDefinition(size)
        this.gameDefinitionRepository.register(gameDefinition)
        return gameDefinition
    }
}