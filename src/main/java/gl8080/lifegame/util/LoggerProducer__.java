package gl8080.lifegame.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer__ {

    public Logger createLogger(InjectionPoint ip) {
        Class<?> targetClass = ip.getMember().getDeclaringClass();
        return LoggerFactory.getLogger(targetClass);
    }
}
