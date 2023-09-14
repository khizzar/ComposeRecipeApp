package com.example.mvvmrecipecompose.presentation.ui.recipeList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mvvmrecipecompose.R
import com.example.mvvmrecipecompose.presentation.ui.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipes.value
                val query = viewModel.recipeQuery.value
                val keyboardController = LocalSoftwareKeyboardController.current

                Column {

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary, shadowElevation = 8.dp
                    ) {

                        Row(modifier = Modifier.fillMaxWidth()) {
                            TextField(value = query,
                                modifier = Modifier.fillMaxWidth(0.9f),
                                onValueChange = { viewModel.onRecipeQueryChange(it) },
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
                                        viewModel.newSearch(query)
                                        keyboardController?.hide()
                                    }
                                )
                            )
                        }

                    }

                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe) {}
                        }
                    }
                }

            }
        }
    }

}