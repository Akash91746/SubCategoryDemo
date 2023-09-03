package com.example.subcategorydemo.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.subcategorydemo.domain.models.Disease
import com.example.subcategorydemo.domain.models.DiseaseType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseItem(
    item: Disease,
    onToggleCheckbox: (DiseaseType) -> Unit,
    onClickExpand: () -> Unit
) {

    Column {
        ListItem(
            headlineText = {
                Text(text = item.title)
            },
            trailingContent = {
                IconToggleButton(
                    checked = item.expanded,
                    onCheckedChange = {
                        onClickExpand()
                    }
                ) {
                    Icon(
                        imageVector = if (item.expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                        contentDescription = if (item.expanded) "Collapse" else "Expand"
                    )
                }
            }
        )
        Divider()

        AnimatedVisibility(visible = item.expanded) {
            Column {
                item.type.map{ type ->
                    DiseaseTypeItem(
                        type = type,
                        isChecked = type.isChecked
                    ) {
                        onToggleCheckbox(type)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseTypeItem(
    type: DiseaseType,
    isChecked: Boolean,
    onToggleCheck: () -> Unit,
) {
    ListItem(
        headlineText = { Text(text = type.name) },
        leadingContent = {
            Checkbox(checked = isChecked, onCheckedChange = { onToggleCheck() })
        }
    )
}