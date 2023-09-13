package com.example.mvvmrecipecompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class RecipeListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Column {
                    Text(text = "This is Recipe List Fragment!", style = TextStyle(
                        fontSize = TextUnit(26f, TextUnitType.Sp)
                    ), modifier = Modifier.align(Alignment.CenterHorizontally))

                    Spacer(modifier = Modifier.padding(16.dp))

                    Button(onClick = {
                                     findNavController().navigate(R.id.recipeFragment)
                    }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "Recipe Fragment!")
                    }
                }
            }
        }
    }

}