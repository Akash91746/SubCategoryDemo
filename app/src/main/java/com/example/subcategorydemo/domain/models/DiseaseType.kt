package com.example.subcategorydemo.domain.models

data class DiseaseType(
    val id: Int,
    val name: String,
    val isChecked: Boolean = false
)
