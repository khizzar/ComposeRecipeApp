package com.example.mvvmrecipecompose.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmrecipecompose.R
import com.example.mvvmrecipecompose.network.models.RecipeService
import com.example.mvvmrecipecompose.ui.theme.MVVMRecipeComposeTheme
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var app: BaseApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val service = Retrofit.Builder()
//            .baseUrl("https://food2fork.ca/api/recipe/")
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            .build()
//            .create(RecipeService::class.java)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val recipe = service.get(
//                token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
//                id = 583
//            )
//            Log.e("Mango", "onCreate: ${recipe.title}")
//        }
//
//        Log.e("Mango", "onCreate: $app")

//        setContent {
//            Column(modifier = Modifier
//                .fillMaxSize()) {
//                Image(painter = painterResource(id = R.drawable.happy_meal),
//                    contentDescription = "happy meal", contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .height(300.dp)
//                        .fillMaxWidth())
//
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                        Text(text = "Happy Meal", style = TextStyle(
//                            fontSize = TextUnit(26f, TextUnitType.Sp)
//                        ))
//
//                        Text(text = "600 Rs", style = TextStyle(
//                            fontSize = TextUnit(17f, TextUnitType.Sp),
//                            color = Color(android.graphics.Color.GREEN)
//                        ), modifier = Modifier.align(Alignment.CenterVertically))
//                    }
//
//                    Spacer(modifier = Modifier.padding(top = 10.dp))
//
//                    Text(text = "500 Calories", style = TextStyle(
//                        fontSize = TextUnit(17f, TextUnitType.Sp)
//                    ))
//
//                    Spacer(modifier = Modifier.padding(top = 10.dp))
//
//                    Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
//                        Text(text = "Order Now")
//                    }
//
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVMRecipeComposeTheme {
        Greeting("Android")
    }
}