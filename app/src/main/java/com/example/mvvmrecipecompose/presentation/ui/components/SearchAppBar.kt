package com.example.mvvmrecipecompose.presentation.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipecompose.presentation.ui.recipeList.FoodCategory
import com.example.mvvmrecipecompose.presentation.ui.recipeList.getAllFoodCategories

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    query: String,
    onRecipeQueryChange: (String) -> Unit,
    onNewSearch: () -> Unit,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    keyboardController: SoftwareKeyboardController
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp
    ) {

        Column {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(value = query,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    onValueChange = { onRecipeQueryChange(it) },
                    label = { Text(text = "Search") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onNewSearch()
                            keyboardController?.hide()
                        }
                    )
                )
            }

            LazyRow(modifier = Modifier.padding(5.dp)) {
                itemsIndexed(
                    items = getAllFoodCategories()
                ) { index, category ->
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory == category,
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                        },
                        onExecuteSearch = onNewSearch,
                    )
                }
            }
        }

    }
}