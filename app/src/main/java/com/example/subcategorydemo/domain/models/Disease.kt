package com.example.subcategorydemo.domain.models

data class Disease(
    val id: Int,
    val title: String,
    val expanded: Boolean = false,
    val type: List<DiseaseType>
)
