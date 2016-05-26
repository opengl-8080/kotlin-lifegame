package gl8080.lifegame.web.exception

import javax.persistence.OptimisticLockException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class OptimisticLockExceptionMapper: ExceptionMapper<OptimisticLockException>{

    override fun toResponse(ex: OptimisticLockException?): Response? {
        return Response.status(Response.Status.CONFLICT).build()
    }
}