package com.example.mvvmrecipecompose.presentation.ui.recipeList

sealed class RecipeListEvent {

    object NewSearchEvent : RecipeListEvent()

    object NextPageEvent : RecipeListEvent()

}
