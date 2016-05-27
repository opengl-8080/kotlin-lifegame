package gl8080.lifegame.web.json

import gl8080.lifegame.web.resource.LifeGameDto
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider

@Provider
@Produces(MediaType.APPLICATION_JSON)
class LifeGameDtoWriter: AbstractJsonWriter<LifeGameDto>()