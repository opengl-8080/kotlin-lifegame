package gl8080.lifegame.web.exception

import gl8080.lifegame.logic.exception.NotFoundEntityException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class NotFoundEntityExceptionMapper: ExceptionMapper<NotFoundEntityException> {

    override fun toResponse(ex: NotFoundEntityException?): Response? {
        return Response.status(Response.Status.NOT_FOUND).build()
    }
}