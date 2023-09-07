package com.example.mvvmrecipecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipecompose.ui.theme.MVVMRecipeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.background(color = Color(R.color.light_grey)).fillMaxSize()) {
                Image(painter = painterResource(id = R.drawable.happy_meal),
                    contentDescription = "happy meal", contentScale = ContentScale.Crop,
                    modifier = Modifier.height(300.dp).fillMaxWidth())

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Happy Meal", style = TextStyle(
                        fontSize = TextUnit(26f, TextUnitType.Sp)
                    ))
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(text = "500 Calories", style = TextStyle(
                        fontSize = TextUnit(17f, TextUnitType.Sp)
                    ))
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(text = "600 Rs", style = TextStyle(
                        fontSize = TextUnit(17f, TextUnitType.Sp),
                        color = Color(android.graphics.Color.GREEN)
                    ))
                }
            }
        }
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