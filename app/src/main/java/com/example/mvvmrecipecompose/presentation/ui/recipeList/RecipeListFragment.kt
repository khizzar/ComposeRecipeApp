package com.example.mvvmrecipecompose.presentation.ui.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.mvvmrecipecompose.presentation.ui.components.CircularIndeterminateProgressBar
import com.example.mvvmrecipecompose.presentation.ui.components.FoodCategoryChip
import com.example.mvvmrecipecompose.presentation.ui.components.RecipeCard
import com.example.mvvmrecipecompose.presentation.ui.components.SearchAppBar
import com.example.mvvmrecipecompose.presentation.ui.recipeList.RecipeListEvent.*
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
                val selectedCategory = viewModel.selectedCategory.value
                val isLoading = viewModel.isLoading.value
                val page = viewModel.page.value
                val keyboardController = LocalSoftwareKeyboardController.current

                Column {

                    keyboardController?.let {
                        SearchAppBar(
                            query = query,
                            onRecipeQueryChange = viewModel::onRecipeQueryChange,
                            onNewSearch = viewModel::goToNextPage,
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            keyboardController = it
                        )
                    }

                    Box(modifier = Modifier.fillMaxSize()) { // box is used just like the framelayout to place one view on top of the other one

                        LazyColumn {
                            itemsIndexed(
                                items = recipes
                            ) { index, recipe ->
                                viewModel.onChangeRecipeScrollPosition(index)
                                if ((index + 1) >= (page * PAGE_SIZE) && !isLoading) {
                                    viewModel.onTriggerEvent(NextPageEvent)
                                }
                                RecipeCard(recipe = recipe) {}
                            }
                        }

                        CircularIndeterminateProgressBar(isDisplayed = isLoading)
                    }
                }

            }
        }
    }

}