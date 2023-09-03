package com.example.subcategorydemo.utils

import com.example.subcategorydemo.domain.models.Disease
import com.example.subcategorydemo.domain.models.DiseaseType

val DemoData = listOf(
    Disease(
        id = 1,
        title = "Endocrine/Hormonal",
        type = listOf(
            DiseaseType(id = 1,name = "Thyroid Disorders"),
            DiseaseType(id = 2,name = "Diabetes")
        )
    ),
    Disease(
        id = 2,
        title = "General Disorders",
        type = listOf(
            DiseaseType(id = 1,name = "Low Immunity"),
            DiseaseType(id = 2,name = "Preventive Diseases"),
            DiseaseType(id = 3,name = "Obesity"),
            DiseaseType(id = 4,name = "Chillblains"),
            DiseaseType(id = 5,name = "Heart Stroke")
        )
    )
)