package gl8080.lifegame.application

import gl8080.lifegame.logic.Game
import gl8080.lifegame.logic.GameRepository
import org.slf4j.Logger
import javax.ejb.Stateless
import javax.inject.Inject

@Stateless
open class SearchGameService {
    @Inject
    lateinit private var logger: Logger
    @Inject
    lateinit private var repository: GameRepository

    open fun search(id: Long): Game {
        this.logger.info("search game (id={})", id)
        return this.repository.search(id)
    }
}