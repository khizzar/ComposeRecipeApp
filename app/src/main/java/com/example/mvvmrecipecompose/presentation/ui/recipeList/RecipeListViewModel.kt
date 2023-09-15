package com.example.mvvmrecipecompose.presentation.ui.recipeList


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipecompose.domain.Recipe
import com.example.mvvmrecipecompose.network.models.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject
constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String,
): ViewModel(){

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    var recipeQuery = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Float = 0f
    var isLoading = mutableStateOf(false)

    init {
        newSearch()
    }

    fun newSearch(){
        viewModelScope.launch {
            isLoading.value = true
            resetSearchState()
            delay(2000)
            val result = repository.search(
                token = token,
                page = 1,
                query = recipeQuery.value
            )
            recipes.value = result
            isLoading.value = false
        }
    }

    fun onRecipeQueryChange(query: String) {
        this.recipeQuery.value = query
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onRecipeQueryChange(category)
    }

    fun onCategoryClickPosition(position: Float) {
        this.categoryScrollPosition = position
    }

    fun resetSearchState() {
        recipes.value = listOf()
        if (selectedCategory.value?.value != recipeQuery.value) {
            clearSearchCategory()
        }
    }

    fun clearSearchCategory() {
        this.selectedCategory.value = null
    }
}