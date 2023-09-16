package com.example.mvvmrecipecompose.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mvvmrecipecompose.presentation.ui.components.CircularIndeterminateProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment: Fragment() {

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getInt("recipeId")?.let { id ->
            viewModel.recipeId.value = id
            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(id))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                val loading = viewModel.loading.value
                val recipe = viewModel.recipe.value

                Box (
                    modifier = Modifier.fillMaxSize()
                ){
                    if (loading && recipe == null) Text(text = "LOADING...")
                    else recipe?.let {
                        RecipeView(
                            recipe = it,
                        )
                    }
                    CircularIndeterminateProgressBar(isDisplayed = loading)

                }
            }
        }
    }

}