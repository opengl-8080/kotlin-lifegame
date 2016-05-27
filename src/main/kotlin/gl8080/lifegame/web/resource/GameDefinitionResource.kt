package gl8080.lifegame.web.resource

import gl8080.lifegame.application.definition.RegisterGameDefinitionService
import gl8080.lifegame.application.definition.RemoveGameDefinitionService
import gl8080.lifegame.application.definition.SearchGameDefinitionService
import gl8080.lifegame.application.definition.UpdateGameDefinitionService
import gl8080.lifegame.util.Maps
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder


@Path("/game/definition")
@ApplicationScoped
open class GameDefinitionResource {
    @Inject
    lateinit private var registerService: RegisterGameDefinitionService
    @Inject
    lateinit private var searchService: SearchGameDefinitionService
    @Inject
    lateinit private var saveService: UpdateGameDefinitionService
    @Inject
    lateinit private var removeService: RemoveGameDefinitionService

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    open fun register(@QueryParam("size") size: Int): Response {
        val gameDefinition = this.registerService.register(size)
        val id = gameDefinition.getId()

        val uri = UriBuilder
                    .fromResource(GameDefinitionResource::class.java)
                    .path(GameDefinitionResource::class.java, "search")
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
        val gameDefinition = this.searchService.search(id)
        return LifeGameDto.of(gameDefinition)
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    open fun update(dto: LifeGameDto?): Response {
        if (dto == null) {
            return Response.status(Response.Status.BAD_REQUEST).build()
        }

        val gameDefinition = this.saveService.update(dto)

        return Response
                    .ok()
                    .entity(Maps.map("version", gameDefinition.getVersion()))
                    .build()
    }

    @DELETE
    @Path("/{id}")
    open fun remove(@PathParam("id") id: Long): Response {
        this.removeService.remove(id)
        return Response.noContent().build()
    }
}