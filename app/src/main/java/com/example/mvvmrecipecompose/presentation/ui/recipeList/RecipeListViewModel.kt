package com.example.mvvmrecipecompose.presentation.ui.recipeList


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipecompose.domain.Recipe
import com.example.mvvmrecipecompose.network.models.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val PAGE_SIZE = 30

@HiltViewModel
class RecipeListViewModel @Inject
constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String,
) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    var recipeQuery = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Float = 0f
    var isLoading = mutableStateOf(false)
    val page = mutableStateOf(1)
    private var recipeListScrollPosition: Int = 0

    init {
        newSearch()
    }

    fun newSearch() {
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

    fun nextPage() {
        viewModelScope.launch {
            // prevent duplicate event due to recompose happening to quickly
            if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                isLoading.value = true
                updatePageNumber()

                // just to show pagination, api is fast
                delay(1000)

                // this will prevent this function to be called on the start because on start newSearch is to be called
                if (page.value > 1) {
                    val result = repository.search(
                        token = token,
                        page = page.value,
                        query = recipeQuery.value
                    )
                    appendRecipes(result)
                }
                isLoading.value = false
            }
        }
    }

    fun onRecipeQueryChange(query: String) {
        this.recipeQuery.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onRecipeQueryChange(category)
    }

    fun onCategoryClickPosition(position: Float) {
        this.categoryScrollPosition = position
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangeRecipeScrollPosition(0)
        if (selectedCategory.value?.value != recipeQuery.value) {
            clearSearchCategory()
        }
    }

    private fun clearSearchCategory() {
        this.selectedCategory.value = null
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val currentList = ArrayList(this.recipes.value)
        currentList.addAll(recipes)
        this.recipes.value = currentList
    }

    private fun updatePageNumber() {
        page.value = page.value + 1
    }

    fun onChangeRecipeScrollPosition(position: Int) {
        recipeListScrollPosition = position
    }

}