package gl8080.lifegame.web.exception

import javax.transaction.RollbackException
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider
import javax.ws.rs.ext.Providers

@Provider
class RollBackExceptionMapper: ExceptionMapper<RollbackException> {

    @Context
    lateinit private var providers: Providers

    override fun toResponse(e: RollbackException?): Response? {
        val cause = e?.cause

        val type = cause?.javaClass as Class<Throwable>

        val exceptionMapper = this.providers.getExceptionMapper(type)

        if (exceptionMapper == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        } else {
            return exceptionMapper.toResponse(cause)
        }
    }
}