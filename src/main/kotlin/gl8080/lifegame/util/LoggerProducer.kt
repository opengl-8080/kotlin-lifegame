package gl8080.lifegame.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.enterprise.inject.spi.InjectionPoint

@ApplicationScoped
class LoggerProducer {

    @Produces
    fun createLogger(ip: InjectionPoint): Logger {
        val targetClass = ip.member.declaringClass
        return LoggerFactory.getLogger(targetClass)
    }
}