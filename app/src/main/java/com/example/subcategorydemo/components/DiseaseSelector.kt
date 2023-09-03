package com.example.subcategorydemo.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.subcategorydemo.MainActivityViewModel
import com.example.subcategorydemo.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DiseaseSelector(
    viewModel: MainActivityViewModel,
) {
    val data by viewModel.diseases.collectAsState()
    val selectedItems by viewModel.selectedItems.collectAsState()

    Column {
        LazyColumn {
            items(data, key = { it.hashCode() }) { item ->
                DiseaseItem(
                    item = item,
                    onToggleCheckbox = {
                        viewModel.toggleSelected(item, it)
                    }
                ) {
                    viewModel.toggleExpanded(item)
                }
            }
        }

        FlowRow {
            selectedItems.map { entry ->
                entry.value.map { type ->
                    ElevatedAssistChip(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        onClick = { viewModel.toggleSelected(entry.key, type) },
                        label = { Text(text = type.name) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = stringResource(
                                    id = R.string.close
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}