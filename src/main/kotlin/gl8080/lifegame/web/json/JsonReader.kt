package gl8080.lifegame.web.json

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import gl8080.lifegame.logic.exception.IllegalParameterException
import gl8080.lifegame.web.resource.LifeGameDto
import java.io.InputStream
import java.lang.reflect.Type
import javax.ws.rs.Consumes
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.ext.MessageBodyReader
import javax.ws.rs.ext.Provider

@Provider
@Consumes(MediaType.APPLICATION_JSON)
class JsonReader: MessageBodyReader<LifeGameDto> {

    private val mapper = ObjectMapper()

    override fun isReadable(clazz: Class<*>?, type: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?): Boolean {
        return mediaType?.isCompatible(MediaType.APPLICATION_JSON_TYPE) ?: false
    }

    override fun readFrom(clazz: Class<LifeGameDto>?, type: Type?, annotations: Array<out Annotation>?, mediaType: MediaType?, header: MultivaluedMap<String, String>?, input: InputStream?): LifeGameDto? {
        try {
            return this.mapper.readValue(input, LifeGameDto::class.java)
        } catch (e: JsonParseException) {
            throw IllegalParameterException("json フォーマットが不正です。")
        }
    }
}