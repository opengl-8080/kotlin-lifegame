package gl8080.lifegame.web.resource

import gl8080.lifegame.application.NextStepService
import gl8080.lifegame.application.RegisterGameService
import gl8080.lifegame.application.RemoveGameService
import gl8080.lifegame.application.SearchGameService
import gl8080.lifegame.util.Maps
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder

@Path("/game")
@ApplicationScoped
open class GameResource {
    @Inject
    lateinit private var registerService: RegisterGameService
    @Inject
    lateinit private var searchServie: SearchGameService
    @Inject
    lateinit private var nextService: NextStepService
    @Inject
    lateinit private var removeService: RemoveGameService

    @POST
    open fun register(@QueryParam("game-definition-id") gameDefinitionId: Long): Response {
        val game = this.registerService.register(gameDefinitionId)
        val id = game.getId()

        val uri = UriBuilder
                    .fromResource(GameResource::class.java)
                    .path(GameResource::class.java, "search")
                    .build(id)

        return Response
                    .created(uri)
                    .entity(Maps.map("id", id))
                    .build()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    open fun search(@PathParam("id") id: Long): LifeGameDto {
        val game = this.searchServie.search(id)
        return LifeGameDto.of(game)
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    open fun next(@PathParam("id") id: Long): LifeGameDto {
        val game = this.nextService.next(id)
        return LifeGameDto.of(game)
    }

    @DELETE
    @Path("/{id}")
    open fun remove(@PathParam("id") id: Long): Response {
        this.removeService.remove(id)
        return Response.ok().build()
    }
}