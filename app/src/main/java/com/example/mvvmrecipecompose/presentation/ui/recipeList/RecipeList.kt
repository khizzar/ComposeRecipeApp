package com.example.mvvmrecipecompose.presentation.ui.recipeList

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mvvmrecipecompose.R
import com.example.mvvmrecipecompose.domain.Recipe
import com.example.mvvmrecipecompose.presentation.ui.components.CircularIndeterminateProgressBar
import com.example.mvvmrecipecompose.presentation.ui.components.RecipeCard
import com.example.mvvmrecipecompose.presentation.ui.recipeList.RecipeListEvent.*

@Composable
fun RecipeList(
    page: Int,
    isLoading: Boolean,
    recipes: List<Recipe>,
    navController: NavController,
    onNextPageCall: (RecipeListEvent) -> Unit,
    onChangeRecipeScrollPosition: (index: Int) -> Unit,
) {

    Box(modifier = Modifier.fillMaxSize()) { // box is used just like the framelayout to place one view on top of the other one

        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                onChangeRecipeScrollPosition(index)
                if ((index + 1) >= (page * PAGE_SIZE) && !isLoading) {
                    onNextPageCall(NextPageEvent)
                }
                RecipeCard(recipe = recipe, onClick = {
                    if (recipe.id != null) {
                        val bundle =  Bundle()
                        bundle.putInt("recipeId", recipe.id)
                        navController.navigate(R.id.action_recipeListFragment_to_recipeFragment, bundle)
                    }
                })
            }
        }

        CircularIndeterminateProgressBar(isDisplayed = isLoading)
    }

}