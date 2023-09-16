package com.example.mvvmrecipecompose.presentation.ui.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipecompose.R
import com.example.mvvmrecipecompose.domain.Recipe
import com.example.mvvmrecipecompose.network.models.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RecipeView(
    recipe: Recipe,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        recipe.featuredImage?.let { url ->
            val image = loadPicture(url = url, defaultImage = R.drawable.empty_plate).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "recipe image", contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(225.dp)
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                recipe.title?.let { title ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ){
                        Text(
                            text = title,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start)
                            ,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        val rank = recipe.rating.toString()
                        Text(
                            text = rank,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .align(Alignment.CenterVertically)
                            ,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                recipe.publisher?.let { publisher ->
                    val updated = recipe.dateUpdated
                    Text(
                        text = if(updated != null) {
                            "Updated ${updated} by ${publisher}"
                        }
                        else {
                            "By ${publisher}"
                        }
                        ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                        ,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                recipe.description?.let { description ->
                    if(description != "N/A"){
                        Text(
                            text = description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                            ,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                for(ingredient in recipe.ingredients){
                    Text(
                        text = ingredient,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                        ,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                recipe.cookingInstructions?.let { instructions ->
                    Text(
                        text = instructions,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                        ,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}