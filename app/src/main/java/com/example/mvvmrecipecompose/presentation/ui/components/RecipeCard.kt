package com.example.mvvmrecipecompose.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipecompose.R
import com.example.mvvmrecipecompose.domain.Recipe
import com.example.mvvmrecipecompose.network.models.util.loadPicture
import com.example.mvvmrecipecompose.presentation.ui.Greeting
import com.example.mvvmrecipecompose.ui.theme.MVVMRecipeComposeTheme

@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Column {

            recipe.featuredImage?.let { url ->
                val image = loadPicture(url = url, defaultImage = R.drawable.empty_plate).value
                image?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "happy meal", contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(225.dp)
                            .fillMaxWidth()
                    )
                }
            }
            recipe.title?.let { title ->
                Row(modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth()) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    val ranking = recipe.rating.toString()
                    Text(
                        text = ranking,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    MVVMRecipeComposeTheme {
        RecipeCard(Recipe(
            featuredImage = "Test Image",
            title = "Test Card",
            rating = 50
        ), {})
    }
}