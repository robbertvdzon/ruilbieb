package com.vdzon.java.entity

import org.jetbrains.exposed.dao.IntIdTable

object IncidentTable : IntIdTable(name = "Incident", columnName = "ID") {
    val timestamp = long("TIMESTAMP")
    val action = integer("ACTION")
}
