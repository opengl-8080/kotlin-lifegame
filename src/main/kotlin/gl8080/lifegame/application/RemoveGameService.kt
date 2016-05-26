package gl8080.lifegame.application

import gl8080.lifegame.logic.GameRepository
import org.slf4j.Logger
import javax.ejb.Stateless
import javax.inject.Inject

@Stateless
open class RemoveGameService {
    @Inject
    lateinit private var logger: Logger
    @Inject
    lateinit private var gameRepository: GameRepository

    open fun remove(id: Long) {
        this.logger.info("remove game (id={})", id)

        val game = this.gameRepository.searchWithLock(id)

        this.gameRepository.remove(game)
    }
}