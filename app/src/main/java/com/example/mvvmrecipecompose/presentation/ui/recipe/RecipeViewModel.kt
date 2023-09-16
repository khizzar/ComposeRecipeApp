package com.example.mvvmrecipecompose.presentation.ui.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mvvmrecipecompose.domain.Recipe
import com.example.mvvmrecipecompose.network.models.repository.RecipeRepository
import com.example.mvvmrecipecompose.presentation.ui.recipe.RecipeEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import androidx.lifecycle.viewModelScope


@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @Named("auth_token") val token: String,
) : ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    val recipeId: MutableState<Int> = mutableStateOf(0)

    val loading = mutableStateOf(false)

    init {
        onTriggerEvent(GetRecipeEvent(recipeId.value))
    }

    fun onTriggerEvent(event: RecipeEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetRecipeEvent -> {
                        if (recipe.value == null) {
                            getRecipe(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Mango", "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private suspend fun getRecipe(id: Int) {
        loading.value = true

        // simulate a delay to show loading
        delay(1000)

        val recipe = recipeRepository.get(token = token, id = id)
        this.recipe.value = recipe

        loading.value = false
    }

}