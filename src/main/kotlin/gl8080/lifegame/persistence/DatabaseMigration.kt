package gl8080.lifegame.persistence

import org.flywaydb.core.Flyway
import org.slf4j.Logger
import javax.annotation.Resource
import javax.inject.Inject
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener
import javax.sql.DataSource

@WebListener
class DatabaseMigration: ServletContextListener {

    @Inject
    lateinit private var logger: Logger

    @Resource(lookup = "java:app/lifegameDS")
    lateinit private var ds: DataSource

    override fun contextInitialized(e: ServletContextEvent?) {
        this.logger.info("initialize database... {}", System.getProperty("user.dir"))

        val flyway = Flyway()
        flyway.dataSource = this.ds
        flyway.migrate()

        this.logger.info("initialize database done.")
    }

    override fun contextDestroyed(p0: ServletContextEvent?) {}
}