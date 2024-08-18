package com.vdzon.java

import com.vdzon.java.entity.DagSummary
import com.vdzon.java.entity.Incident
import com.vdzon.java.entity.IncidentEntity
import com.vdzon.java.entity.IncidentTable
import com.vdzon.java.entity.Summary
import org.h2.jdbcx.JdbcDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


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


    fun getSummary(): Summary {
        return transaction {
            val all = IncidentEntity.find { IncidentTable.action.eq(1) }.map { Incident(it.timestamp, it.action) }

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            val thisMorningTimestamp = calendar.time.time

            val today = all.filter { it.timestamp > thisMorningTimestamp }.size
            var index = thisMorningTimestamp
            val list: MutableList<DagSummary> = mutableListOf(DagSummary(getDateString(index), today))
            for (day in 1..100) {
                val end = index
                index = index - 24 * 60 * 60 * 1000
                val count = all.filter { it.timestamp > index && it.timestamp < end }.size
                list.add(DagSummary(getDateString(index), count))
            }
            val summary = Summary(all.size, list)
            summary
        }

    }

    fun getDateString(date: Long): String {
        val pattern = "MM-dd-yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val d = Date(date)
        val str = simpleDateFormat.format(d)
        return str
    }
}