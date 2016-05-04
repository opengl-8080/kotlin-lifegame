package gl8080.lifegame.logic

import java.io.Serializable
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity : Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    protected constructor()

    protected  constructor(id: Long) {
        this.id = id
    }

    fun getId(): Long? {
        return this.id
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (!(o is AbstractEntity)) return false

        if (o.id == null) {
            return false
        } else {
            return this.id == o.id
        }
    }

    override fun hashCode() : Int {
        if (this.id == null) {
            return super.hashCode()
        } else {
            return Objects.hashCode(this.id)
        }
    }
}