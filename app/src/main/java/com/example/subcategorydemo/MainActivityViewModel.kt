package com.example.subcategorydemo

import androidx.lifecycle.ViewModel
import com.example.subcategorydemo.domain.models.Disease
import com.example.subcategorydemo.domain.models.DiseaseType
import com.example.subcategorydemo.utils.DemoData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel : ViewModel() {

    private val _selectedItems = MutableStateFlow<HashMap<Int, Set<DiseaseType>>>(HashMap())
    val selectedItems = _selectedItems.asStateFlow()

    private val _diseases = MutableStateFlow(DemoData)
    val diseases: StateFlow<List<Disease>> = _diseases.asStateFlow()

    fun toggleSelected(disease: Disease, type: DiseaseType) {
        val currentList = _diseases.value.toMutableList()

        val index = currentList.indexOfFirst { it.id == disease.id }
        val typeIndex = disease.type.indexOfFirst { it.id == type.id }

        if (index == -1 || typeIndex == -1) return

        val newType = type.copy(isChecked = !type.isChecked)
        val newTypeList = disease.type.toMutableList()
        newTypeList[typeIndex] = newType
        currentList[index] = disease.copy(type = newTypeList)

        _diseases.value = currentList

        updateSelectedItems(disease.id, newType)
    }

    fun toggleSelected(diseaseId: Int,type:DiseaseType) {
        val index = _diseases.value.indexOfFirst { it.id == diseaseId }

        if(index!= -1) {
            toggleSelected(_diseases.value[index],type)
        }
    }

    private fun updateSelectedItems(diseaseId: Int, type: DiseaseType) {
        val data = type.copy(isChecked = true)

        val currentMap = HashMap(_selectedItems.value)

        val set = currentMap.getOrDefault(diseaseId, HashSet()).toMutableSet()

        if (set.contains(data)) {
            set.remove(data)
        } else {
            set.add(data)
        }

        currentMap[diseaseId] = set

        _selectedItems.value = currentMap
    }

    fun toggleExpanded(disease: Disease) {
        val data = disease.copy(expanded = !disease.expanded)

        val currentList = _diseases.value.toMutableList()

        val index = currentList.indexOfFirst { it.id == data.id }

        if (index != -1) {
            currentList[index] = data
            _diseases.value = currentList
        }
    }
}