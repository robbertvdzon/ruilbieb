package com.vdzon.java

import com.vdzon.java.entity.Incident
import com.vdzon.java.entity.IncidentEntity
import com.vdzon.java.entity.IncidentTable
import org.h2.jdbcx.JdbcDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object Database {
    const val url = "jdbc:h2:file:~/ruilbieb_db;MODE=MySQL"

    fun init() {
        val dataSource = JdbcDataSource()
        dataSource.setURL(url)
        Database.connect({ dataSource.connection })
        createTable()
    }

    private fun createTable() {
        return transaction {
            SchemaUtils.create(IncidentTable)
        }
    }

    fun deurOpen() {
        return transaction {
            val incident = Incident(System.currentTimeMillis(), 0)
            IncidentEntity.new { load(incident) }
        }
    }

    fun deurDicht() {
        return transaction {
            val incident = Incident(System.currentTimeMillis(), 1)
            IncidentEntity.new { load(incident) }
        }
    }

    fun getAll() = transaction {
        IncidentEntity.all().map { Incident(it.timestamp, it.action) }
    }.toList()
}