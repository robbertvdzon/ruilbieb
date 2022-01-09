package com.vdzon.java.entity

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class IncidentEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<IncidentEntity>(IncidentTable)
    var timestamp by IncidentTable.timestamp
    var action by IncidentTable.action

    fun load(incident: Incident): IncidentEntity {
        timestamp = incident.timestamp
        action = incident.action
        return this
    }
}
