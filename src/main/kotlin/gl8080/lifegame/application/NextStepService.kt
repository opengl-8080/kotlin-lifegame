package gl8080.lifegame.application

import gl8080.lifegame.logic.Game
import gl8080.lifegame.logic.GameRepository
import org.slf4j.Logger
import javax.ejb.Stateless
import javax.inject.Inject

@Stateless
open class NextStepService {
    @Inject
    lateinit private var logger: Logger
    @Inject
    lateinit private var repository: GameRepository

    open fun next(id: Long): Game {
        this.logger.info("next step game (id={})", id)

        val game = this.repository.searchWithLock(id)
        game.initializeNeighborCells()
        game.nextStep()

        return game
    }
}
