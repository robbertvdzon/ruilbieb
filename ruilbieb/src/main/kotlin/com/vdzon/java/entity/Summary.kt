package com.vdzon.java.entity

data class Summary (val total: Int, val dagSummary: List<DagSummary>)
data class DagSummary(val dag: String, val count: Int)