package com.vdzon.java.entity

import java.util.Date

data class Incident (val timestamp: Long, val action: Int){
    val timestampString get() = Date(timestamp).toString()
    val actionString get() = when (action){
        0 -> "Dicht"
        1 -> "Open"
        else -> "?"
    }
}