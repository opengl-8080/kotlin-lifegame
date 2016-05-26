package gl8080.lifegame.web.exception

import gl8080.lifegame.logic.exception.BusinessException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class BusinessExceptionMapper: ExceptionMapper<BusinessException> {

    override fun toResponse(ex: BusinessException?): Response? {
        return Response.status(Response.Status.BAD_REQUEST).build()
    }
}