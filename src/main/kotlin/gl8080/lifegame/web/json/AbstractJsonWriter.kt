package gl8080.lifegame.web.json

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.OutputStream
import java.lang.reflect.Type
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.ext.MessageBodyWriter


abstract class AbstractJsonWriter<T>: MessageBodyWriter<T> {
    private val mapper = ObjectMapper()

    override fun writeTo(dto: T, clazz: Class<*>?, type: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?, header: MultivaluedMap<String, Any>?, output: OutputStream?) {
        this.mapper.writeValue(output, dto)
    }

    override fun getSize(dto: T, clazz: Class<*>?, type: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?): Long {
        return -1
    }

    override fun isWriteable(clazz: Class<*>?, type: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?): Boolean {
        return mediaType?.isCompatible(MediaType.APPLICATION_JSON_TYPE) ?: false
    }
}