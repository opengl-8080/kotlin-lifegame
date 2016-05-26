package gl8080.lifegame.application

import gl8080.lifegame.logic.Game
import gl8080.lifegame.logic.GameRepository
import gl8080.lifegame.logic.definition.GameDefinitionRepository
import org.slf4j.Logger
import javax.ejb.Stateless
import javax.inject.Inject

@Stateless
open class RegisterGameService {
    @Inject
    lateinit private var logger: Logger
    @Inject
    lateinit private var gameDefinitionRepository: GameDefinitionRepository
    @Inject
    lateinit private var gameRepository: GameRepository

    open fun register(gameDefinitionId: Long): Game {
        this.logger.info("save game (gameDefinitionId={})", gameDefinitionId)

        val gameDefinition = this.gameDefinitionRepository.search(gameDefinitionId)

        val game = Game(gameDefinition)
        this.gameRepository.register(game)

        return game
    }
}